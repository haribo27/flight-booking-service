package ru.zubcov.flightbookingservice.bookingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.zubcov.flightbookingservice.commondto.ErrorDTO;
import ru.zubcov.flightbookingservice.userservice.exception.EntityNotFound;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleEntityNotFound(EntityNotFound e) {
        return new ResponseEntity<>(new ErrorDTO(e.getMessage(), 404), HttpStatus.NOT_FOUND);
    }
}
