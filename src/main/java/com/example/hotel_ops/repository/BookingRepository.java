package com.example.hotel_ops.repository;

import com.example.hotel_ops.entity.Booking;
import com.example.hotel_ops.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    @Query("""
            SELECT COUNT(b) FROM Booking b
            WHERE b.room.id = :roomId AND b.status = 'CONFIRMED'
            AND (:checkInDate < b.checkOutDate AND :checkOutDate > b.checkInDate)
            """)
    Long existsOverlappingBooking(
            Long roomId,
            LocalDate checkInDate,
            LocalDate checkOutDate
    );

    List<Booking> findByUserEmail(String email);

    List<Booking> findByUserEmailAndStatus(String email, BookingStatus status);

    List<Booking> findByStatus(BookingStatus status);
}
