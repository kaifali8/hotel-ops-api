package com.example.hotel_ops.entity;

import com.example.hotel_ops.enums.RoomStatus;
import com.example.hotel_ops.enums.RoomType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "rooms")
@NoArgsConstructor
public class Room extends BaseEntity {

    @Column(name = "room_number",nullable = false,unique = true)
    private Integer roomNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type",nullable = false,length = 20)
    private RoomType roomType;

    @Column(name = "price_per_night",nullable = false)
    private BigDecimal pricePerNight;

    @Column(nullable = false)
    private Integer capacity;

    @Column(name = "floor_number",nullable = false)
    private Integer floorNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private RoomStatus status;

    @Column(nullable = false)
    private Boolean active=true;
}
