package com.example.hotel_ops.exception;

public class ResourceAlreadyExistsException extends RuntimeException{
    public ResourceAlreadyExistsException(String message){
        super(message);
    }
}
