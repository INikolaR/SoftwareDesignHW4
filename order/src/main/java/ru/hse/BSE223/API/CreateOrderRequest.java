package ru.hse.BSE223.API;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreateOrderRequest {
    private int fromStationId;
    private int toStationId;
}
