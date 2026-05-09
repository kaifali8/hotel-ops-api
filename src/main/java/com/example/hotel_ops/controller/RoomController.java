package com.example.hotel_ops.controller;

import com.example.hotel_ops.dto.request.RoomCreateRequest;
import com.example.hotel_ops.dto.request.RoomStatusUpdateRequest;
import com.example.hotel_ops.dto.request.RoomUpdateRequest;
import com.example.hotel_ops.dto.response.RoomResponse;
import com.example.hotel_ops.entity.Room;
import com.example.hotel_ops.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Room", description = "Room APIs")
public class RoomController {

    private final RoomService roomService;

    @Operation(summary = "Create a new room")
    @PostMapping("/admin/room/create")
    public ResponseEntity<RoomResponse> createRoom(@Valid @RequestBody RoomCreateRequest request){
        RoomResponse roomResponse=roomService.createRoom(request);
        return new ResponseEntity<>(roomResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing room")
    @PutMapping("/admin/rooms/{id}")
    public ResponseEntity<RoomResponse> updateRoom(@PathVariable Long id,@Valid @RequestBody RoomUpdateRequest request){
        RoomResponse roomResponse=roomService.updateRoom(id,request);
        return ResponseEntity.ok(roomResponse);
    }

    @Operation(summary = "Deactivate an existing active room")
    @PatchMapping("/admin/rooms/{id}/deactivate")
    public ResponseEntity<Void> deactivateRoom(@PathVariable Long id){
        roomService.deactivateRoom(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update the room status")
    @PatchMapping("/admin/rooms/{id}/status")
    public ResponseEntity<RoomResponse> updateRoomStatus(@PathVariable Long id,@Valid @RequestBody RoomStatusUpdateRequest request){
        RoomResponse roomResponse=roomService.updateRoomStatus(id,request);
        return ResponseEntity.ok(roomResponse);
    }

    @Operation(summary = "Check all the available rooms")
    @GetMapping("/rooms/available")
    public ResponseEntity<List<RoomResponse>> getAvailableRooms(){
        List<RoomResponse> responses = roomService.getAvailableRooms();
        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "Get all the existing rooms")
    @GetMapping("/admin/rooms")
    public ResponseEntity<List<RoomResponse>> getAllRooms(){
        List<RoomResponse> responses=roomService.getAllRooms();
        return ResponseEntity.ok(responses);
    }

}
