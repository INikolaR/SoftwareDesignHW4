package ru.hse.BSE223.HW4.Controllers;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.BSE223.HW4.Controllers.API.*;
import ru.hse.BSE223.HW4.Exceptions.TestException;
import ru.hse.BSE223.HW4.Exceptions.UnauthorizedException;
import ru.hse.BSE223.HW4.Repositories.Data.User;
import ru.hse.BSE223.HW4.Services.AuthenticationService;
import ru.hse.BSE223.HW4.Validators.InputValidator;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthenticationService authenticationService;
    private final InputValidator validator;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation=JwtAuthenticationResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation= ErrorResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation= ErrorResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation= ErrorResponse.class)
                            )
                    })
    })
    @PostMapping("/sign-up")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody SignUpRequest request) {
        validator.validateNickname(request.getUsername());
        validator.validateEmail(request.getEmail());
        validator.validatePassword(request.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.signUp(request));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation=JwtAuthenticationResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation= ErrorResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation= ErrorResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation= ErrorResponse.class)
                            )
                    })
    })
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody SignInRequest request) {
        validator.validateEmail(request.getEmail());
        return authenticationService.signIn(request);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation=UserDataResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation= ErrorResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation= ErrorResponse.class)
                            )
                    })
    })
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
