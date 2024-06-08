package ru.hse.BSE223.Exceptions;

public class BadStationIdException extends RuntimeException {
    public BadStationIdException(String message) {
        super(message);
    }
}
