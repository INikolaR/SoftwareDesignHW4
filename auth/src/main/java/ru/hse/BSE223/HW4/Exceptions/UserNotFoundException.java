package ru.hse.BSE223.HW4.Exceptions;

import ru.hse.BSE223.HW4.Repositories.Data.User;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
