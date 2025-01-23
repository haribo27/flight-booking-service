package ru.zubcov.flightbookingservice.apigatewayservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.openapitools.model.BookingRequestDTO;
import org.openapitools.model.BookingResponseDTO;
import org.openapitools.model.BookingStatusUpdateDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {

    BookingResponseDTO mapToBookingResponseDTO(BookingRequestDTO request);

    @Mapping(target = "userid", source = "userId")
    @Mapping(target = "bookingId", source = "bookingId")
    @Mapping(target = "isConfirmed", source = "isConfirmed")
    BookingStatusUpdateDTO toBookingStatusUpdateDTO(long userId, long bookingId, Boolean isConfirmed);

}
