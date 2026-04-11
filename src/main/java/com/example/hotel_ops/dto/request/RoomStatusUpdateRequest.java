package com.example.hotel_ops.dto.request;

import com.example.hotel_ops.enums.RoomStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoomStatusUpdateRequest {
    @NotNull
    private RoomStatus status;
}
