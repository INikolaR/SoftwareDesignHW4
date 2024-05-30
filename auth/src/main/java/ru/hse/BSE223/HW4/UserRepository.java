package ru.hse.BSE223.HW4;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class UserRepository {
    private final ArrayList<User> users = new ArrayList<>();

    public User getUserByUsername(String username) {
        List<User> u = users.stream().filter(uu -> Objects.equals(uu.getUsername(), username)).toList();
        if (!u.isEmpty()) {
            return u.get(0);
        }
        return null;
    }

    public void addUser(User user) {
        if (users.stream().noneMatch(u -> Objects.equals(u.getUsername(), user.getUsername()))) {
            users.add(user);
        }
    }
    public User getByUsernameAndPassword(String username, String password) {
        List<User> filteredUsers = users.stream().filter(u -> Objects.equals(u.getUsername(), username) && passwordEncoder().matches(password, u.getPassword())).toList();
        if (!filteredUsers.isEmpty()) {
            return filteredUsers.get(0);
        }
        return null;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
