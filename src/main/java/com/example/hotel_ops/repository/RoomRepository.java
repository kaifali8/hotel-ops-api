package com.example.hotel_ops.repository;

import com.example.hotel_ops.entity.Room;
import com.example.hotel_ops.enums.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    boolean existsByRoomNumber(int roomNumber);

    List<Room> findAllByActiveTrue();

    List<Room> findByActiveTrueAndStatus(RoomStatus status);
}
