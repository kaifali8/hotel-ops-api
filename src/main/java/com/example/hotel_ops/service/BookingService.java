package com.example.hotel_ops.service;


import com.example.hotel_ops.dto.request.BookingCreateRequest;
import com.example.hotel_ops.dto.response.BookingResponse;

import java.util.List;

public interface BookingService {

    BookingResponse createBooking(String userEmail, BookingCreateRequest request);

    List<BookingResponse> getMyBookings(String userEmail);
}
