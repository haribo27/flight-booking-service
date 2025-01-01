package ru.zubcov.flightbookingservice.commondto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingResponseDTO {

    private final String name;
    private final String surname;
    private final String patronymic;
    private final String documentNumber;
    private final String departmentCity;
    private final String arrivalCity;
    private final LocalDateTime flightDateTime;
    private final String seatNumber;
    private String bookingCode;
}
