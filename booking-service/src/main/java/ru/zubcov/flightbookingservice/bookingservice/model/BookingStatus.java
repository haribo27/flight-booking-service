package ru.zubcov.flightbookingservice.bookingservice.model;

public enum BookingStatus {

    AWAITING_CONFIRMATION,
    AWAITING_PAYMENT,
    CONFIRMED,
    CANCELLED,
    REJECTED_FLIGHT_NOT_FOUND,
    REJECTED_NO_MORE_SEATS_ON_FLIGHT
}
