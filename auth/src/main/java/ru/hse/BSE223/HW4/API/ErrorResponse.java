package ru.hse.BSE223.HW4.API;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {
    private String errorMessage;
}
