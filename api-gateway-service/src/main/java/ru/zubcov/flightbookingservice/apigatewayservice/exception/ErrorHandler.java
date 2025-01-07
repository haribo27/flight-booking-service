package ru.zubcov.flightbookingservice.apigatewayservice.exception;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.zubcov.flightbookingservice.commondto.*;

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
    public ResponseEntity<ErrorDTO> handleEntityNotFound(EntityNotFound e) {
        log.warn("Entity not found {}", e.getMessage());
        return new ResponseEntity<>(new ErrorDTO(e.getMessage(), 404), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleUnexpectedServiceError(UnexpectedServiceException e) {
        log.warn("Unexpected service error {}", e.getMessage());
        return new ResponseEntity<>(new ErrorDTO(e.getMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleUnauthorizedException(UnauthorizedExceptionDTO e) {
        log.warn("Unauthorized exception {}", e.getMessage() + e.getLocalizedMessage());
        return new ResponseEntity<>(new ErrorDTO(e.getMessage(), 401), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleBookingStatusException(BookingStatusException e) {
        log.warn("Booking status exception {}", e.getMessage());
        return new ResponseEntity<>(new ErrorDTO(e.getMessage(), 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handleFeignExceptions(FeignException e) {
        if (e.status() == 404) {
            return new ResponseEntity<>(new ErrorDTO("Entity not found", 404), HttpStatus.NOT_FOUND);
        }
        log.warn("User already exists with this email {}",e.getMessage());
        return new ResponseEntity<>(new ErrorDTO(e.getMessage() + e.getLocalizedMessage(), 409),
                HttpStatus.CONFLICT);
    }
}
