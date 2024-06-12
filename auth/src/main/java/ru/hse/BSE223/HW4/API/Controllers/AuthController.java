package ru.hse.BSE223.HW4.API.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import ru.hse.BSE223.HW4.API.JwtAuthenticationResponse;
import ru.hse.BSE223.HW4.API.SignInRequest;
import ru.hse.BSE223.HW4.API.SignUpRequest;
import ru.hse.BSE223.HW4.API.UserDataResponse;

public interface AuthController {
    ResponseEntity<JwtAuthenticationResponse> signUp(SignUpRequest request);
    JwtAuthenticationResponse signIn(SignInRequest request);
    UserDataResponse getUserData(HttpServletRequest request);
}
