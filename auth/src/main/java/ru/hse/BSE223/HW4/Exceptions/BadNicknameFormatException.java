package ru.hse.BSE223.HW4.Exceptions;

public class BadNicknameFormatException extends RuntimeException {
    public BadNicknameFormatException(String message) {
        super(message);
    }
}
