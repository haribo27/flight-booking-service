package ru.zubcov.flightbookingservice.apigatewayservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.zubcov.flightbookingservice.commondto.BookingResponseDTO;
import ru.zubcov.flightbookingservice.commondto.BookingRequestDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {

    BookingResponseDTO mapToBookingDto(BookingRequestDTO request);
}
