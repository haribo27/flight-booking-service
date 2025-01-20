package ru.zubcov.flightbookingservice.commondto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "DTO для бронирования")
public class BookingDTO {

    @Schema(description = "Идентификатор бронирования")
    private Long id;
    @Schema(description = "Почта пользователя")
    private String userEmail;
    @Schema(description = "Аэропорт отправления")
    private String departureAirport;
    @Schema(description = "Аэропорт прибытия")
    private String arrivalAirport;
    @Schema(description = "Время вылета")
    private LocalDateTime departureTime;
    @Schema(description = "Время прибытия")
    private LocalDateTime arrivalTime;
    @Schema(description = "Место в самолете")
    private String seatNumber;
    @Schema(description = "Статус бронирования")
    private String bookingStatus;
}
