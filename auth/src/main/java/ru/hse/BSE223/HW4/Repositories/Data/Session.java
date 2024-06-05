package ru.hse.BSE223.HW4.Repositories.Data;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="user_id")
    private int userId;
    @Column(name="token")
    private String token;
    @Column(name="expires")
    private Timestamp expires;
    public Session(int userId, String token, Timestamp expires) {
        this.userId = userId;
        this.token = token;
        this.expires = expires;
    }
}
