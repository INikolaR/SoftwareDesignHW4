package ru.hse.BSE223.HW4.Data.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.BSE223.HW4.Data.User;

public interface JPAUserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
