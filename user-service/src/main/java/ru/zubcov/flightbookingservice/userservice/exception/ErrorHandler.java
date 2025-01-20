package ru.zubcov.flightbookingservice.userservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.zubcov.flightbookingservice.commondto.ErrorDTO;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("Wrong user input data");
        return new ResponseEntity<>(new ErrorDTO(e.getMessage(), e.getStatusCode().value()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleEntityNotFoundException(EntityNotFound e) {
        log.warn("Entity not found error");
        return new ResponseEntity<>(new ErrorDTO(e.getMessage(), 404),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.warn("User with this login or email already exists");
        return new ResponseEntity<>(new ErrorDTO(e.getMessage(), 409), HttpStatus.CONFLICT);
    }
}
