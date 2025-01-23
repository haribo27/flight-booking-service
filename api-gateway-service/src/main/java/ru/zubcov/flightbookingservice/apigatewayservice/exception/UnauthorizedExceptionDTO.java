package ru.zubcov.flightbookingservice.apigatewayservice.exception;

public class UnauthorizedExceptionDTO extends RuntimeException {
    public UnauthorizedExceptionDTO(String message) {
        super(message);
    }
}
