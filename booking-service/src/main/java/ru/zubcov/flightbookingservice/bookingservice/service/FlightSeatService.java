package ru.zubcov.flightbookingservice.bookingservice.service;

public interface FlightSeatService {

    String confirmFlightSeat(String seatNumber, Long flightId);

    void cancelSeatReservation(long flightId, String seatNumber);
}
