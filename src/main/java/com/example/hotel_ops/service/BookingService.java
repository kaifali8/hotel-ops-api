package com.example.hotel_ops.service;


import com.example.hotel_ops.dto.request.BookingCreateRequest;
import com.example.hotel_ops.dto.response.BookingResponse;
import com.example.hotel_ops.entity.Booking;
import com.example.hotel_ops.enums.BookingStatus;

import java.util.List;

public interface BookingService {

    BookingResponse createBooking(String userEmail, BookingCreateRequest request);

    List<BookingResponse> getMyBookings(String userEmail, BookingStatus status);

    void cancelBooking(Long bookingId, String userEmail);

    List<BookingResponse> getAllBookings(BookingStatus status);
}
