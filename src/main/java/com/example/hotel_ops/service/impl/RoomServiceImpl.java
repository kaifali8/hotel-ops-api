package com.example.hotel_ops.service.impl;

import com.example.hotel_ops.dto.request.RoomCreateRequest;
import com.example.hotel_ops.dto.response.RoomResponse;
import com.example.hotel_ops.entity.Room;
import com.example.hotel_ops.enums.RoomStatus;
import com.example.hotel_ops.exception.ResourceAlreadyExistsException;
import com.example.hotel_ops.repository.RoomRepository;
import com.example.hotel_ops.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public RoomResponse createRoom(RoomCreateRequest request) {
        if (roomRepository.existsByRoomNumber(request.getRoomNumber())){
            throw new ResourceAlreadyExistsException("Room already exists with this room number");
        }
        Room room=new Room();
        room.setRoomType(request.getRoomType());
        room.setRoomNumber(request.getRoomNumber());
        room.setCapacity(request.getCapacity());
        room.setFloorNumber(request.getFloorNumber());
        room.setPricePerNight(request.getPricePerNight());
        room.setStatus(RoomStatus.valueOf("AVAILABLE"));
        room.setActive(true);

        Room savedRoom=roomRepository.save(room);
        return RoomResponse.builder()
                .id(savedRoom.getId())
                .roomNumber(savedRoom.getRoomNumber())
                .floorNumber(savedRoom.getFloorNumber())
                .roomType(savedRoom.getRoomType())
                .pricePerNight(savedRoom.getPricePerNight())
                .capacity(savedRoom.getCapacity())
                .status(savedRoom.getStatus())
                .active(savedRoom.getActive())
                .build();
    }

    @Override
    public List<RoomResponse> getAllRooms() {
        List<Room> rooms = roomRepository.findAllByActiveTrue();
        List<RoomResponse> roomResponses = new ArrayList<>();
        for (Room room : rooms) {
            RoomResponse roomResponse = RoomResponse.builder()
                    .id(room.getId())
                    .roomType(room.getRoomType())
                    .roomNumber(room.getRoomNumber())
                    .floorNumber(room.getFloorNumber())
                    .status(room.getStatus())
                    .capacity(room.getCapacity())
                    .pricePerNight(room.getPricePerNight())
                    .active(room.getActive())
                    .build();
            roomResponses.add(roomResponse);
        }
        return roomResponses;
    }

    @Override
    public List<RoomResponse> getAvailableRooms() {
        List<Room> rooms = roomRepository.findByActiveTrueAndStatus(RoomStatus.AVAILABLE);
        List<RoomResponse> roomResponses = new ArrayList<>();
        for (Room room : rooms) {
            RoomResponse roomResponse = RoomResponse.builder()
                    .id(room.getId())
                    .roomType(room.getRoomType())
                    .roomNumber(room.getRoomNumber())
                    .floorNumber(room.getFloorNumber())
                    .status(room.getStatus())
                    .capacity(room.getCapacity())
                    .pricePerNight(room.getPricePerNight())
                    .active(room.getActive())
                    .build();
            roomResponses.add(roomResponse);
        }
        return roomResponses;
    }
}
