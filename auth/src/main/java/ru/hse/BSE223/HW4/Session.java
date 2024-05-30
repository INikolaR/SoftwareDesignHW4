package ru.hse.BSE223.HW4;

import lombok.*;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    private int id;
    private int user_id;
    private String token;
    private Timestamp expires;
}
