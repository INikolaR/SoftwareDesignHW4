package ru.hse.BSE223.Controllers.API;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {
    private String errorMessage;
}
