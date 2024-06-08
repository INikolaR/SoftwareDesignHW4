package ru.hse.BSE223.Controllers.API;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetInfoResponse {
    private Timestamp date;
    private String from;
    private String to;
    private int status;
}
