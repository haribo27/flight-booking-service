package ru.zubcov.flightbookingservice.bookingservice.service;


import org.openapitools.model.BookingDTO;
import org.openapitools.model.BookingRequestDTO;
import org.openapitools.model.BookingStatusUpdateDTO;

import java.util.Set;

public interface BookingService {

    void createBooking(BookingRequestDTO request);

    Set<BookingDTO> getBookingInfo(long userId);

    void updateBookingStatus(BookingStatusUpdateDTO bookingStatusUpdateRequest);


}
