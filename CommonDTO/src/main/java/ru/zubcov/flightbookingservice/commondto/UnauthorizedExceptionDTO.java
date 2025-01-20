package ru.zubcov.flightbookingservice.commondto;

public class UnauthorizedExceptionDTO extends RuntimeException {
    public UnauthorizedExceptionDTO(String message) {
        super(message);
    }
}
