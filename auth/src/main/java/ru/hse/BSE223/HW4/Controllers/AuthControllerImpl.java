package ru.hse.BSE223.HW4.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.BSE223.HW4.Controllers.API.UserDataResponse;
import ru.hse.BSE223.HW4.Exceptions.TestException;
import ru.hse.BSE223.HW4.Exceptions.UnauthorizedException;
import ru.hse.BSE223.HW4.Repositories.Data.User;
import ru.hse.BSE223.HW4.Services.AuthenticationService;
import ru.hse.BSE223.HW4.Controllers.API.JwtAuthenticationResponse;
import ru.hse.BSE223.HW4.Controllers.API.SignInRequest;
import ru.hse.BSE223.HW4.Controllers.API.SignUpRequest;
import ru.hse.BSE223.HW4.Validators.InputValidator;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthenticationService authenticationService;
    private final InputValidator validator;
    @GetMapping("/hello")
    public String getHello() {
        return "Hello!";
    }
    @GetMapping("/bye")
    public ResponseEntity<String> getBye() {
        throw new TestException("LLL");
//        return new ResponseEntity<String>("Bye!", HttpStatus.OK);
//        return new ResponseEntity<String>("Test", HttpStatus.I_AM_A_TEAPOT);
    }

    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody SignUpRequest request) {
        validator.validateNickname(request.getUsername());
        validator.validateEmail(request.getEmail());
        validator.validatePassword(request.getPassword());
        return authenticationService.signUp(request);
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody SignInRequest request) {
        validator.validateEmail(request.getEmail());
        return authenticationService.signIn(request);
    }

    @GetMapping("/get-user-data")
    public UserDataResponse getUserData(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null) {
            throw new UnauthorizedException("Unauthorized");
        }
        User user = authenticationService.getUserData(header.substring(7));
        return new UserDataResponse(user.getUsername(), user.getEmail(), user.getCreated());
    }
}
