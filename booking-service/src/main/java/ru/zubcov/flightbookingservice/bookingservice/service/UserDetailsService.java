package ru.zubcov.flightbookingservice.bookingservice.service;

import org.openapitools.model.BookingRequestDTO;
import ru.zubcov.flightbookingservice.bookingservice.model.User;
import ru.zubcov.flightbookingservice.bookingservice.model.UserDetails;

public interface UserDetailsService {

    UserDetails saveUserDetails(BookingRequestDTO request, User user);
}
