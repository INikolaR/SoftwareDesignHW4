package ru.hse.BSE223.HW4.Validators;

import org.springframework.stereotype.Component;
import ru.hse.BSE223.HW4.Exceptions.BadEmailFormatException;
import ru.hse.BSE223.HW4.Exceptions.BadNicknameFormatException;
import ru.hse.BSE223.HW4.Exceptions.BadPasswordFormatException;

import java.util.regex.Pattern;

@Component
public class InputValidator {
    private final Pattern emailPattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    private final Pattern passwordPattern = Pattern.compile("^(?=.*\\d{1})(?=.*[a-z]{1})(?=.*[A-Z]{1})(?=.*[!@#$%^&*{|}?~_=+.-]{1})(?=.*[^a-zA-Z0-9])(?!.*\\s).{8,256}$");
    public void validatePassword(String password) {
        if (!passwordPattern.matcher(password).matches()) {
            throw new BadPasswordFormatException("Password is invalid! Needed:\nAt least 8 characters\nAt least one uppercase letter\nAt least one lowercase letter\nAt least one digit\nAt least one special character");
        }
    }

    public void validateEmail(String email) {
        if (!emailPattern.matcher(email).matches()) {
            throw new BadEmailFormatException("Bad email format! Input valid email");
        }
    }

    public void validateNickname(String nickname) {
        if (nickname.isBlank()) {
            throw new BadNicknameFormatException("Nickname should not be empty!");
        }
    }
}
