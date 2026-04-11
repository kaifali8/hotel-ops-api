package com.example.hotel_ops.dto.request;

import com.example.hotel_ops.enums.RoomType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RoomUpdateRequest {
    private RoomType roomType;
    private BigDecimal pricePerNight;
    private Integer capacity;
    private Integer floorNumber;
}
