package ru.hse.BSE223.HW4;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class SessionRepository {
    private final ArrayList<Session> sessions = new ArrayList<>();

    public void addSession(Session session) {
        sessions.add(session);
    }
    public Session getSessionByToken(String token) {
        List<Session> s = sessions.stream().filter(ss -> Objects.equals(ss.getToken(), token)).toList();
        if (!s.isEmpty()) {
            return s.get(0);
        }
        return null;
    }

}
