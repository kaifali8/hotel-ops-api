package com.example.hotel_ops.exception;


public class BookingConflictException extends RuntimeException{
    public BookingConflictException(String message){
        super(message);
    }
}
