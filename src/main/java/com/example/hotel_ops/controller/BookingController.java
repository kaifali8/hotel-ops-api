package com.example.hotel_ops.controller;

import com.example.hotel_ops.dto.request.BookingCreateRequest;
import com.example.hotel_ops.dto.response.BookingResponse;
import com.example.hotel_ops.enums.BookingStatus;
import com.example.hotel_ops.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/bookings")
    public ResponseEntity<BookingResponse> createBooking(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody BookingCreateRequest request){
        return ResponseEntity.ok(
                bookingService.createBooking(userDetails.getUsername(), request)
        );
    }

    @GetMapping("/bookings/my")
    public ResponseEntity<List<BookingResponse>> getMyBookings(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(required = false)BookingStatus status){
        return ResponseEntity.ok(
                bookingService.getMyBookings(userDetails.getUsername(),status)
        );
    }

    @PatchMapping("/bookings/{bookingId}/cancel")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long bookingId,@AuthenticationPrincipal UserDetails userDetails){
        String email = userDetails.getUsername();
        bookingService.cancelBooking(bookingId,email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/bookings")
    public ResponseEntity<List<BookingResponse>> getAllBookings(@RequestParam(required = false) BookingStatus status){
        return ResponseEntity.ok(bookingService.getAllBookings(status));
    }
}
