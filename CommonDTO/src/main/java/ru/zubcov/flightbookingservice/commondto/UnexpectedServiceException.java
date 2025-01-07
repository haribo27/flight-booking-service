package ru.zubcov.flightbookingservice.commondto;

public class UnexpectedServiceException extends RuntimeException {
    public UnexpectedServiceException(String message) {
        super(message);
    }
}
