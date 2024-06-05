package ru.hse.BSE223.HW4.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.BSE223.HW4.Repositories.Data.Session;

public interface JPASessionRepository extends JpaRepository<Session, Integer> {
    Session findByToken(String token);
}
