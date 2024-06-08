package ru.hse.BSE223.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.BSE223.Repositories.Data.Order;

import java.util.List;

public interface JPAOrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByEmail(String email);
}
