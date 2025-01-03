package ru.zubcov.flightbookingservice.apigatewayservice.exception;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.zubcov.flightbookingservice.commondto.ErrorDTO;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("Wrong user input data");
        return new ResponseEntity<>(new ErrorDTO(e.getMessage(), e.getStatusCode().value()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleUserAlreadyExists(FeignException e) {
        log.warn("User already exists with this email");
        return new ResponseEntity<>(new ErrorDTO(e.getMessage(), 409),
                HttpStatus.CONFLICT);
    }
}
