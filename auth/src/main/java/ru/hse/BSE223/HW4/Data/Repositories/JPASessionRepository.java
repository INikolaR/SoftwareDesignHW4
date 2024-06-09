package ru.hse.BSE223.HW4.Data.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.BSE223.HW4.Data.Session;

public interface JPASessionRepository extends JpaRepository<Session, Integer> {
    Session findByToken(String token);
}
