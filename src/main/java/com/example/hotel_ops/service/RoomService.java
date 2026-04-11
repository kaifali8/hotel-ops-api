package com.example.hotel_ops.service;

import com.example.hotel_ops.dto.request.RoomCreateRequest;
import com.example.hotel_ops.dto.request.RoomStatusUpdateRequest;
import com.example.hotel_ops.dto.request.RoomUpdateRequest;
import com.example.hotel_ops.dto.response.RoomResponse;
import com.example.hotel_ops.enums.RoomStatus;

import java.util.List;

public interface RoomService {
    RoomResponse createRoom(RoomCreateRequest request);

    RoomResponse updateRoom(Long id, RoomUpdateRequest request);

    void deactivateRoom(Long id);

    RoomResponse updateRoomStatus(Long id, RoomStatusUpdateRequest status);

    List<RoomResponse> getAllRooms();

    List<RoomResponse> getAvailableRooms();


}
