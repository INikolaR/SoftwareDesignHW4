package ru.hse.BSE223.Controllers.API;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetInfoResponse {
    private int userId;
    private Date date;
    private String from;
    private String to;
    private int status;
}
