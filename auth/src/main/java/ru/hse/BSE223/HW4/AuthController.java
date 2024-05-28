package ru.hse.BSE223.HW4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @GetMapping("/hello")
    public String getHello() {
        return "Hello!";
    }
}
