package ru.hse.BSE223.Controllers.API;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreateOrderRequest {
    private String from;
    private String to;
}
