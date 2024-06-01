package ru.hse.BSE223.HW4.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hse.BSE223.HW4.Controllers.API.JwtAuthenticationResponse;
import ru.hse.BSE223.HW4.Controllers.API.SignInRequest;
import ru.hse.BSE223.HW4.Controllers.API.SignUpRequest;
import ru.hse.BSE223.HW4.Repositories.Data.Session;
import ru.hse.BSE223.HW4.Repositories.Data.User;
import ru.hse.BSE223.HW4.Repositories.SessionRepository;
import ru.hse.BSE223.HW4.Repositories.UserRepository;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final SessionRepository sessionRepository;
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        java.util.Date date= new java.util.Date();
        var user = User.builder()
                .id(userRepository.getNextId())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .created(new Timestamp(date.getTime())).build();

        userRepository.addUser(user);


        Date expires = Date.from(new Date().toInstant()
                .plus(1, ChronoUnit.DAYS));
        var jwt = jwtService.generateJwt(user, expires);
        sessionRepository.addSession(new Session(sessionRepository.getNextId(), user.getId(), jwt, new Timestamp(expires.getTime())));
        return new JwtAuthenticationResponse(jwt);
    }
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        User user = userRepository.getByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (user != null) {
            Date expires = Date.from(new Date().toInstant()
                    .plus(1, ChronoUnit.DAYS));
            String jwt = jwtService.generateJwt(user, expires);
            sessionRepository.addSession(new Session(sessionRepository.getNextId(), user.getId(), jwt, new Timestamp(expires.getTime())));
            return new JwtAuthenticationResponse(jwt);
        }
        return new JwtAuthenticationResponse("");
    }

    public User getUserData(String token) {
        Session session = sessionRepository.getSessionByToken(token);
        if (session == null) {
            return null;
        }
        int userId = session.getUserId();
        return userRepository.getById(userId);
    }
}
