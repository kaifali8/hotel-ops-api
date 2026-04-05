package com.example.hotel_ops.dto.request;

import com.example.hotel_ops.enums.RoomType;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class RoomCreateRequest {

    @NotNull(message = "Room number is required")
    private Integer roomNumber;

    @NotNull(message = "Room type is required")
    private RoomType roomType;

    @NotNull(message = "Price per night is required")
    private BigDecimal pricePerNight;

    @NotNull(message = "Capacity is required")
    @Positive(message = "Capacity must be greater than 0")
    private Integer capacity;

    @NotNull(message = "Floor number is required")
    @Min(value = 0,message = "Floor number cannot be negative")
    private Integer floorNumber;
}
