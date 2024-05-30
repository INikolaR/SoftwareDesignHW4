package ru.hse.BSE223.HW4;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        java.util.Date date= new java.util.Date();
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .created(new Timestamp(date.getTime())).build();

        userRepository.addUser(user);

        var jwt = jwtService.generateJwt(user);
        return new JwtAuthenticationResponse(jwt);
    }
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        User user = userRepository.getByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (user != null) {
            String jwt = jwtService.generateJwt(user);
            return new JwtAuthenticationResponse(jwt);
        }
        return new JwtAuthenticationResponse("");
    }
}
