package ru.hse.BSE223.HW4.Controllers.API;

import lombok.Data;

@Data
public class SignInRequest {
    private String username;
    private String password;
}
