package ru.zubcov.flightbookingservice.bookingservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.openapitools.model.BookingDTO;
import ru.zubcov.flightbookingservice.bookingservice.model.Booking;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {

    @Mapping(target = "id", expression = "java(LongToBigDecimal(booking.getId()) != null ? LongToBigDecimal(booking.getId()) : null)")
    @Mapping(target = "userEmail", expression = "java(booking.getUser() != null ? booking.getUser().getEmail() : null)")
    @Mapping(target = "departmentAirport", expression = "java(booking.getFlight() != null ? booking.getFlight().getDepartureAirport() : null)")
    @Mapping(target = "arrivalAirport", expression = "java(booking.getFlight() != null ? booking.getFlight().getArrivalAirport() : null)")
    @Mapping(target = "departureTime", expression = "java(formatLocalDateTime(booking.getFlight() != null ? booking.getFlight().getDepartureTime() : null))")
    @Mapping(target = "arrivalTime", expression = "java(formatLocalDateTime(booking.getFlight() != null ? booking.getFlight().getArrivalTime() : null))")
    @Mapping(target = "seatNumber", expression = "java(booking.getSeat() != null ? booking.getSeat() : null)")
    @Mapping(target = "bookingStatus", expression = "java(booking.getStatus() != null ? booking.getStatus() : null)")
    BookingDTO mapToBookingDTO(Booking booking);

    default String formatLocalDateTime(LocalDateTime dateTime) {
        if (dateTime != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Пример формата
            return dateTime.format(formatter);
        }
        return null;
    }

    default BigDecimal LongToBigDecimal(Long id) {
        return BigDecimal.valueOf(id);
    }
}