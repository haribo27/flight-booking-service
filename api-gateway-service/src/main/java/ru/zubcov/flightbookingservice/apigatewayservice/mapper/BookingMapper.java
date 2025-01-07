package ru.zubcov.flightbookingservice.apigatewayservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.zubcov.flightbookingservice.commondto.BookingRequestDTO;
import ru.zubcov.flightbookingservice.commondto.BookingResponseDTO;
import ru.zubcov.flightbookingservice.commondto.BookingStatusUpdateDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {

    BookingResponseDTO mapToBookingResponseDTO(BookingRequestDTO request);

    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "bookingId", source = "bookingId")
    @Mapping(target = "isConfirmed", source = "isConfirmed")
    BookingStatusUpdateDTO toBookingStatusUpdateDTO(long userId, long bookingId, Boolean isConfirmed);

}
