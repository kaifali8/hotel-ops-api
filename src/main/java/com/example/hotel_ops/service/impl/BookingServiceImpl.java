package com.example.hotel_ops.service.impl;

import com.example.hotel_ops.dto.request.BookingCreateRequest;
import com.example.hotel_ops.dto.response.BookingResponse;
import com.example.hotel_ops.dto.response.RoomResponse;
import com.example.hotel_ops.dto.response.UserResponse;
import com.example.hotel_ops.entity.Booking;
import com.example.hotel_ops.entity.Room;
import com.example.hotel_ops.entity.User;
import com.example.hotel_ops.enums.BookingStatus;
import com.example.hotel_ops.enums.RoomStatus;
import com.example.hotel_ops.exception.BookingConflictException;
import com.example.hotel_ops.exception.ResourceNotFoundException;
import com.example.hotel_ops.repository.BookingRepository;
import com.example.hotel_ops.repository.RoomRepository;
import com.example.hotel_ops.repository.UserRepository;
import com.example.hotel_ops.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Override
    public BookingResponse createBooking(String userEmail, BookingCreateRequest request) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));

        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(()-> new ResourceNotFoundException("Room not found"));

        if (!room.getActive() || room.getStatus() != RoomStatus.AVAILABLE){
            throw new RuntimeException("Room is not available for booking");
        }

        if (request.getCheckInDate().isBefore(LocalDate.now())){
            throw new RuntimeException("Check in date cannot be in the past");
        }

        if (request.getCheckInDate().isAfter(request.getCheckOutDate())){
            throw new RuntimeException("Check-out must be after check-in");
        }

        boolean isBooked= bookingRepository.existsOverlappingBooking(
                room.getId(), request.getCheckInDate(), request.getCheckOutDate()) > 0;

        if(isBooked){
            throw  new BookingConflictException("Room is already booked for selected dates");
        }

        long days= ChronoUnit.DAYS.between(
                request.getCheckInDate(),
                request.getCheckOutDate()
        );

        BigDecimal totalPrice=room.getPricePerNight().multiply(BigDecimal.valueOf(days));

        Booking booking= new Booking();
        booking.setUser(user);
        booking.setRoom(room);
        booking.setCheckInDate(request.getCheckInDate());
        booking.setCheckOutDate(request.getCheckOutDate());
        booking.setTotalPrice(totalPrice);
        booking.setStatus(BookingStatus.CONFIRMED);

        Booking savedBooking = bookingRepository.save(booking);

        UserResponse userResponse= UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .active(user.getActive())
                .build();

        RoomResponse roomResponse=RoomResponse.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .roomType(room.getRoomType())
                .floorNumber(room.getFloorNumber())
                .pricePerNight(room.getPricePerNight())
                .capacity(room.getCapacity())
                .active(room.getActive())
                .status(room.getStatus())
                .build();

        return BookingResponse.builder()
                .id(savedBooking.getId())
                .user(userResponse)
                .room(roomResponse)
                .checkInDate(savedBooking.getCheckInDate())
                .checkOutDate(savedBooking.getCheckOutDate())
                .totalPrice(savedBooking.getTotalPrice())
                .status(savedBooking.getStatus())
                .build();
    }

    @Override
    public List<BookingResponse> getMyBookings(String userEmail,BookingStatus status) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
        List<Booking> bookings = new ArrayList<>();
        if (status != null){
            bookings=bookingRepository.findByUserEmailAndStatus(userEmail,status);
        }else {
            bookings= bookingRepository.findByUserEmail(userEmail);
        }
        List<BookingResponse> bookingResponses= new ArrayList<>();

        UserResponse userResponse= UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .active(user.getActive())
                .build();


        for(Booking booking: bookings){

            Room room=booking.getRoom();
            RoomResponse roomResponse=RoomResponse.builder()
                    .id(room.getId())
                    .roomNumber(room.getRoomNumber())
                    .roomType(room.getRoomType())
                    .floorNumber(room.getFloorNumber())
                    .pricePerNight(room.getPricePerNight())
                    .capacity(room.getCapacity())
                    .active(room.getActive())
                    .status(room.getStatus())
                    .build();

            BookingResponse bookingResponse = BookingResponse.builder()
                    .id(booking.getId())
                    .user(userResponse)
                    .room(roomResponse)
                    .checkInDate(booking.getCheckInDate())
                    .checkOutDate(booking.getCheckOutDate())
                    .totalPrice(booking.getTotalPrice())
                    .status(booking.getStatus())
                    .build();
            bookingResponses.add(bookingResponse);
        }
        return bookingResponses;
    }

    @Override
    public void cancelBooking(Long bookingId, String userEmail) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(()-> new ResourceNotFoundException("Booking not found"));
        if (!booking.getUser().getEmail().equals(userEmail)){
            throw new RuntimeException("You're not allowed to cancel this booking");
        }

        if (BookingStatus.CANCELLED.equals(booking.getStatus())){
            throw new RuntimeException("Booking is already cancelled");
        }

        if (!booking.getCheckInDate().isAfter(LocalDate.now())) {
            throw new RuntimeException("You cannot cancel the past or ongoing bookings");
        }

        if (!BookingStatus.PENDING.equals(booking.getStatus()) && !BookingStatus.CONFIRMED.equals(booking.getStatus())){
            throw new RuntimeException("You cannot cancel booking after the check in");
        }


        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }

    @Override
    public List<BookingResponse> getAllBookings(BookingStatus status) {
        List<Booking> bookings= new ArrayList<>();
        if (status != null){
            bookings=bookingRepository.findByStatus(status);
        }else {
            bookings= bookingRepository.findAll();
        }
        List<BookingResponse> bookingResponses= new ArrayList<>();

        for(Booking booking: bookings){
            Room room=booking.getRoom();
            RoomResponse roomResponse=RoomResponse.builder()
                    .id(room.getId())
                    .roomNumber(room.getRoomNumber())
                    .roomType(room.getRoomType())
                    .floorNumber(room.getFloorNumber())
                    .pricePerNight(room.getPricePerNight())
                    .capacity(room.getCapacity())
                    .active(room.getActive())
                    .status(room.getStatus())
                    .build();
            User user=booking.getUser();
            UserResponse userResponse= UserResponse.builder()
                    .id(user.getId())
                    .fullName(user.getFullName())
                    .email(user.getEmail())
                    .phoneNumber(user.getPhoneNumber())
                    .role(user.getRole())
                    .active(user.getActive())
                    .build();

            BookingResponse bookingResponse = BookingResponse.builder()
                    .id(booking.getId())
                    .user(userResponse)
                    .room(roomResponse)
                    .checkInDate(booking.getCheckInDate())
                    .checkOutDate(booking.getCheckOutDate())
                    .totalPrice(booking.getTotalPrice())
                    .status(booking.getStatus())
                    .build();
            if (booking.getStatus().equals(status)){
                bookingResponses.add(bookingResponse);
            } else if (status==null) {
                bookingResponses.add(bookingResponse);
            }
        }
        return bookingResponses;
    }
}
