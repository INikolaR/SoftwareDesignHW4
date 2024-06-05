package ru.hse.BSE223.HW4.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hse.BSE223.HW4.Controllers.API.UserDataResponse;
import ru.hse.BSE223.HW4.Repositories.Data.User;
import ru.hse.BSE223.HW4.Services.AuthenticationService;
import ru.hse.BSE223.HW4.Controllers.API.JwtAuthenticationResponse;
import ru.hse.BSE223.HW4.Controllers.API.SignInRequest;
import ru.hse.BSE223.HW4.Controllers.API.SignUpRequest;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    @GetMapping("/hello")
    public String getHello() {
        return "Hello!";
    }
    @GetMapping("/bye")
    public String getBye() {
        return "Bye!";
    }

    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody SignInRequest request) {
        return authenticationService.signIn(request);
    }

    @GetMapping("/get-user-data")
    public UserDataResponse getUserData(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        System.out.println(header);
        if (header == null) {
            return new UserDataResponse();
        }
        User user = authenticationService.getUserData(header.substring(7));
        if (user == null) {
            return new UserDataResponse();
        }
        return new UserDataResponse(user.getUsername(), user.getEmail(), user.getCreated());
    }
}
