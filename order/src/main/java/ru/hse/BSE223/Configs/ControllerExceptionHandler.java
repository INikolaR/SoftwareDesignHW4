package ru.hse.BSE223.Configs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.hse.BSE223.Exceptions.BadOrderIdException;
import ru.hse.BSE223.Exceptions.BadStationIdException;
import ru.hse.BSE223.Exceptions.EqualSourceDestinationException;
import ru.hse.BSE223.Exceptions.UnauthorizedException;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
    @ExceptionHandler(BadOrderIdException.class)
    public ResponseEntity<String> handleBadOrderIdException(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
    @ExceptionHandler(BadStationIdException.class)
    public ResponseEntity<String> handleBadStationIdException(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(EqualSourceDestinationException.class)
    public ResponseEntity<String> handleEqualSourceDestinationException(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
}
