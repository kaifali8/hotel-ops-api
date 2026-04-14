package com.example.hotel_ops.dto.response;

import com.example.hotel_ops.entity.Room;
import com.example.hotel_ops.entity.User;
import com.example.hotel_ops.enums.BookingStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class BookingResponse {
    private Long id;
    private UserResponse user;
    private RoomResponse room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BigDecimal totalPrice;
    private BookingStatus status;
}
