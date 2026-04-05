package com.example.hotel_ops.dto.response;

import com.example.hotel_ops.enums.RoomStatus;
import com.example.hotel_ops.enums.RoomType;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class RoomResponse {

    private Long id;
    private int roomNumber;
    private RoomType roomType;
    private BigDecimal pricePerNight;
    private int capacity;
    private int floorNumber;
    private RoomStatus status;
    private Boolean active;
}
