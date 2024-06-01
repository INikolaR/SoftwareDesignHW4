package ru.hse.BSE223.HW4.Controllers.API;

import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String email;
    private String password;
}
