package ru.hse.BSE223.HW4.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.hse.BSE223.HW4.Controllers.API.JwtAuthenticationResponse;
import ru.hse.BSE223.HW4.Controllers.API.SignInRequest;
import ru.hse.BSE223.HW4.Controllers.API.SignUpRequest;
import ru.hse.BSE223.HW4.Controllers.API.UserDataResponse;

public interface AuthController {
    ResponseEntity<JwtAuthenticationResponse> signUp(SignUpRequest request);
    JwtAuthenticationResponse signIn(SignInRequest request);
    UserDataResponse getUserData(HttpServletRequest request);
}
