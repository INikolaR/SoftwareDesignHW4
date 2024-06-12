package ru.hse.BSE223.Exceptions;

public class BadOrderIdException extends RuntimeException {
    public BadOrderIdException(String message) {
        super(message);
    }
}
