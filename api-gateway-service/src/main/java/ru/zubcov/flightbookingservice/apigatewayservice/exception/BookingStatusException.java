package ru.zubcov.flightbookingservice.apigatewayservice.exception;

public class BookingStatusException extends RuntimeException {
    public BookingStatusException(String message) {
        super(message);
    }
}
