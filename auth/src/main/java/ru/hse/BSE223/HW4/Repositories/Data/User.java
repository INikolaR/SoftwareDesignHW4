package ru.hse.BSE223.HW4.Repositories.Data;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.sql.Timestamp;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="nickname")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="email")
    private String email;
    @Column(name="created")
    private Timestamp created;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.created = new Timestamp((new Date()).getTime());
    }
}
