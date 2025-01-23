package ru.zubcov.flightbookingservice.apigatewayservice.exception;

public class UnexpectedServiceException extends RuntimeException {
    public UnexpectedServiceException(String message) {
        super(message);
    }
}
