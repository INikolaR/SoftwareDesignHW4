package ru.hse.BSE223.HW4.API;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
}
