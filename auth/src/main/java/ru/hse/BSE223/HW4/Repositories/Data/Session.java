package ru.hse.BSE223.HW4.Repositories.Data;

import lombok.*;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    private int id;
    private int userId;
    private String token;
    private Timestamp expires;
}
