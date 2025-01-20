package ru.zubcov.flightbookingservice.bookingservice.service;

import ru.zubcov.flightbookingservice.bookingservice.model.User;
import ru.zubcov.flightbookingservice.bookingservice.model.UserDetails;
import ru.zubcov.flightbookingservice.commondto.BookingRequestDTO;

public interface UserDetailsService {

    UserDetails saveUserDetails(BookingRequestDTO request, User user);
}
