package com.example.hotel_ops.controller;

import com.example.hotel_ops.dto.request.RoomCreateRequest;
import com.example.hotel_ops.dto.request.RoomStatusUpdateRequest;
import com.example.hotel_ops.dto.request.RoomUpdateRequest;
import com.example.hotel_ops.dto.response.RoomResponse;
import com.example.hotel_ops.entity.Room;
import com.example.hotel_ops.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/admin/room/create")
    public ResponseEntity<RoomResponse> createRoom(@Valid @RequestBody RoomCreateRequest request){
        RoomResponse roomResponse=roomService.createRoom(request);
        return new ResponseEntity<>(roomResponse, HttpStatus.CREATED);
    }

    @PutMapping("/admin/rooms/{id}")
    public ResponseEntity<RoomResponse> updateRoom(@PathVariable Long id,@Valid @RequestBody RoomUpdateRequest request){
        RoomResponse roomResponse=roomService.updateRoom(id,request);
        return ResponseEntity.ok(roomResponse);
    }

    @PatchMapping("/admin/rooms/{id}/deactivate")
    public ResponseEntity<Void> deactivateRoom(@PathVariable Long id){
        roomService.deactivateRoom(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/admin/rooms/{id}/status")
    public ResponseEntity<RoomResponse> updateRoomStatus(@PathVariable Long id,@Valid @RequestBody RoomStatusUpdateRequest request){
        RoomResponse roomResponse=roomService.updateRoomStatus(id,request);
        return ResponseEntity.ok(roomResponse);
    }

    @GetMapping("/rooms/available")
    public ResponseEntity<List<RoomResponse>> getAvailableRooms(){
        List<RoomResponse> responses = roomService.getAvailableRooms();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/admin/rooms")
    public ResponseEntity<List<RoomResponse>> getAllRooms(){
        List<RoomResponse> responses=roomService.getAllRooms();
        return ResponseEntity.ok(responses);
    }

}
