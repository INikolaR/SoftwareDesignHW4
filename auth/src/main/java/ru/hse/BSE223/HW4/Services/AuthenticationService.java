package ru.hse.BSE223.HW4.Services;

import ru.hse.BSE223.HW4.Controllers.API.JwtAuthenticationResponse;
import ru.hse.BSE223.HW4.Controllers.API.SignInRequest;
import ru.hse.BSE223.HW4.Controllers.API.SignUpRequest;
import ru.hse.BSE223.HW4.Repositories.Data.User;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest request);
    JwtAuthenticationResponse signIn(SignInRequest request);
    User getUserData(String token);

}
