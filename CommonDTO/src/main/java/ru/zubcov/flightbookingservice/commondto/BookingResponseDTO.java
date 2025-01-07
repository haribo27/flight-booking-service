package ru.zubcov.flightbookingservice.commondto;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class BookingResponseDTO {

    String name;
    String surname;
    String patronymic;
    String departmentAirport;
    String arrivalAirport;
    LocalDateTime flightDateTime;
    String seatNumber;
}
