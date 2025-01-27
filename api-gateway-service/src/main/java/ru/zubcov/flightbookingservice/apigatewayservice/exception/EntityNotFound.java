package ru.zubcov.flightbookingservice.apigatewayservice.exception;

public class EntityNotFound extends RuntimeException {
    public EntityNotFound(String message) {
        super(message);
    }
}
