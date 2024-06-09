package ru.hse.BSE223.HW4.Services;

import ru.hse.BSE223.HW4.API.JwtAuthenticationResponse;
import ru.hse.BSE223.HW4.API.SignInRequest;
import ru.hse.BSE223.HW4.API.SignUpRequest;
import ru.hse.BSE223.HW4.Data.User;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest request);
    JwtAuthenticationResponse signIn(SignInRequest request);
    User getUserData(String token);

}
