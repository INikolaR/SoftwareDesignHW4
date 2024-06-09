package ru.hse.BSE223.HW4.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hse.BSE223.HW4.Controllers.API.JwtAuthenticationResponse;
import ru.hse.BSE223.HW4.Controllers.API.SignInRequest;
import ru.hse.BSE223.HW4.Controllers.API.SignUpRequest;
import ru.hse.BSE223.HW4.Exceptions.IncorrectPasswordException;
import ru.hse.BSE223.HW4.Exceptions.InvalidTokenException;
import ru.hse.BSE223.HW4.Exceptions.UserAlreadyExistsException;
import ru.hse.BSE223.HW4.Exceptions.UserNotFoundException;
import ru.hse.BSE223.HW4.Repositories.Data.Session;
import ru.hse.BSE223.HW4.Repositories.Data.User;
import ru.hse.BSE223.HW4.Repositories.JPASessionRepository;
import ru.hse.BSE223.HW4.Repositories.JPAUserRepository;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JPAUserRepository userRepository;
    private final JwtService jwtService;
    private final JPASessionRepository sessionRepository;
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        User user = new User(request.getUsername(), passwordEncoder().encode(request.getPassword()), request.getEmail());
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserAlreadyExistsException("User with this email already exists!");
        }
        Date expires = Date.from(new Date().toInstant()
                .plus(1, ChronoUnit.DAYS));
        var jwt = jwtService.generateJwt(user, expires);
        Session session = new Session(user.getId(), jwt, new Timestamp(expires.getTime()));
        sessionRepository.save(session);
        return new JwtAuthenticationResponse(jwt);
    }
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new UserNotFoundException("No user with such email!");
        }
        if (!passwordEncoder().matches(request.getPassword(), user.getPassword())) {
            throw new IncorrectPasswordException("Incorrect password!");
        }
        Date expires = Date.from(new Date().toInstant()
                .plus(1, ChronoUnit.DAYS));
        String jwt = jwtService.generateJwt(user, expires);
        sessionRepository.save(new Session(user.getId(), jwt, new Timestamp(expires.getTime())));
        return new JwtAuthenticationResponse(jwt);
    }

    public User getUserData(String token) {
        Session session = sessionRepository.findByToken(token);
        if (session == null) {
            throw new InvalidTokenException("Bad token!");
        }
        int userId = session.getUserId();
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("No user with specified token!");
        }
    }

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
