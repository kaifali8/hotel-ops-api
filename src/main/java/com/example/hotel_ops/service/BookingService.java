package com.example.hotel_ops.service;


import com.example.hotel_ops.dto.request.BookingCreateRequest;
import com.example.hotel_ops.dto.response.BookingResponse;
import com.example.hotel_ops.entity.Booking;
import com.example.hotel_ops.enums.BookingStatus;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookingService {

    BookingResponse createBooking(String userEmail, BookingCreateRequest request);

    Page<BookingResponse> getMyBookings(String userEmail, BookingStatus status, int page, int size);

    void cancelBooking(Long bookingId, String userEmail);

    Page<BookingResponse> getAllBookings(BookingStatus status,int page, int size);
}
