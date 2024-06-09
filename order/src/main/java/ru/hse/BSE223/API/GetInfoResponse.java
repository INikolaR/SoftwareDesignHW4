package ru.hse.BSE223.API;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hse.BSE223.API.Enums.OrderStatus;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetInfoResponse {
    private Timestamp date;
    private String from;
    private String to;
    private OrderStatus status;
}
