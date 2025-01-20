package ru.zubcov.flightbookingservice.bookingservice.service;

import ru.zubcov.flightbookingservice.commondto.BookingDTO;
import ru.zubcov.flightbookingservice.commondto.BookingRequestDTO;
import ru.zubcov.flightbookingservice.commondto.BookingStatusUpdateDTO;

import java.util.Set;

public interface BookingService {

    void createBooking(BookingRequestDTO request);

    Set<BookingDTO> getBookingInfo(long userId);

    void updateBookingStatus(BookingStatusUpdateDTO bookingStatusUpdateRequest);


}
