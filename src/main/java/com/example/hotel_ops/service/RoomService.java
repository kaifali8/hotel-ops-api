package com.example.hotel_ops.service;

import com.example.hotel_ops.dto.request.RoomCreateRequest;
import com.example.hotel_ops.dto.response.RoomResponse;

import java.util.List;

public interface RoomService {
    RoomResponse createRoom(RoomCreateRequest request);

    List<RoomResponse> getAllRooms();

    List<RoomResponse> getAvailableRooms();
}
