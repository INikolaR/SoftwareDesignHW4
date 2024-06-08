package ru.hse.BSE223.Repositories.Data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="email")
    private String email;
    @Column(name="from_station_id")
    private int fromStationId;
    @Column(name="to_station_id")
    private int toStationId;
    @Column(name="status")
    private int status;
    @Column(name="created")
    private Timestamp created;
    public Order(String email, int fromStationId, int toStationId, int status) {
        this.email = email;
        this.fromStationId = fromStationId;
        this.toStationId = toStationId;
        this.status = status;
        this.created = new Timestamp(System.currentTimeMillis());
    }
    public Order(String email, int fromStationId, int toStationId, int status, Timestamp created) {
        this.email = email;
        this.fromStationId = fromStationId;
        this.toStationId = toStationId;
        this.status = status;
        this.created = created;
    }
}
