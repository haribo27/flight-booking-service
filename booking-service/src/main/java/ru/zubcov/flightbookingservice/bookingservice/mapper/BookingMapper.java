package ru.zubcov.flightbookingservice.bookingservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.zubcov.flightbookingservice.bookingservice.model.Booking;
import ru.zubcov.flightbookingservice.commondto.BookingDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {

    @Mapping(target = "id", expression = "java(booking.getId() != null ? booking.getId() : null)")
    @Mapping(target = "userEmail", expression = "java(booking.getUser() != null ? booking.getUser().getEmail() : null)")
    @Mapping(target = "departureAirport", expression = "java(booking.getFlight() != null ? booking.getFlight().getDepartureAirport() : null)")
    @Mapping(target = "arrivalAirport", expression = "java(booking.getFlight() != null ? booking.getFlight().getArrivalAirport() : null)")
    @Mapping(target = "departureTime", expression = "java(booking.getFlight() != null ? booking.getFlight().getDepartureTime() : null)")
    @Mapping(target = "arrivalTime", expression = "java(booking.getFlight() != null ? booking.getFlight().getArrivalTime() : null)")
    @Mapping(target = "seatNumber", expression = "java(booking.getSeat() != null ? booking.getSeat() : null)")
    @Mapping(target = "bookingStatus", expression = "java(booking.getStatus() != null ? booking.getStatus().toString() : null)")
    BookingDTO mapToBookingDTO(Booking booking);
}