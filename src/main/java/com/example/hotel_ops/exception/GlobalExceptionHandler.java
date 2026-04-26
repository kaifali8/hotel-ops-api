package com.example.hotel_ops.exception;

import com.example.hotel_ops.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleResourceAlreadyExists(ResourceAlreadyExistsException exception){
        Map<String, String> error = new HashMap<>();
        error.put("message",exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationError(MethodArgumentNotValidException exception){
        Map<String,String> error= new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            error.put(fieldError.getField(),fieldError.getDefaultMessage());
        });

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundError(ResourceNotFoundException e){
        return new ResponseEntity<>(
                new ErrorResponse(false, e.getMessage(),
                    404, LocalDateTime.now()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> handleGenericError(Exception e){
        Map<String,String> error = new HashMap<>();
        error.put("message",e.getMessage());
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BookingConflictException.class)
    public ResponseEntity<Map<String,String>> handleBookingConflict(BookingConflictException e){
        Map<String ,String> error = new HashMap<>();
        error.put("message",e.getMessage());
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }
}
