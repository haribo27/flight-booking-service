package ru.zubcov.flightbookingservice.commondto;

public class EntityNotFound extends RuntimeException {
    public EntityNotFound(String message) {
        super(message);
    }
}
