package ru.hse.BSE223.HW4.Exceptions;

public class BadEmailFormatException extends RuntimeException {
    public BadEmailFormatException(String message) {
        super(message);
    }
}
