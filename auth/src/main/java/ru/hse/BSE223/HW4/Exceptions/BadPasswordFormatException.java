package ru.hse.BSE223.HW4.Exceptions;

public class BadPasswordFormatException extends RuntimeException {
    public BadPasswordFormatException(String message) {
        super(message);
    }
}
