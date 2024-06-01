package ru.hse.BSE223.HW4.Controllers.API;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataResponse {
    private String username;
    private String email;
    private Timestamp dateTime;
}
