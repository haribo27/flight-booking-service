package ru.zubcov.flightbookingservice.commondto;

public class BookingStatusException extends RuntimeException {
    public BookingStatusException(String message) {
        super(message);
    }
}
