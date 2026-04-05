package com.example.hotel_ops.controller;

import com.example.hotel_ops.dto.request.RoomCreateRequest;
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
