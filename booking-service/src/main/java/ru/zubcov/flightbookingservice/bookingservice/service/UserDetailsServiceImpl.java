package ru.zubcov.flightbookingservice.bookingservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.zubcov.flightbookingservice.bookingservice.model.User;
import ru.zubcov.flightbookingservice.bookingservice.model.UserDetails;
import ru.zubcov.flightbookingservice.bookingservice.repository.UserDetailsRepository;
import ru.zubcov.flightbookingservice.commondto.BookingRequestDTO;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails saveUserDetails(BookingRequestDTO request, User user) {
        return userDetailsRepository.findByDocumentNumber(request.getDocumentNumber())
                .orElseGet(() -> {
                    UserDetails newUserDetails = new UserDetails();
                    newUserDetails.setUser(user);
                    newUserDetails.setFirstName(request.getName());
                    newUserDetails.setSurName(request.getSurname());
                    newUserDetails.setPatronymic(request.getPatronymic());
                    newUserDetails.setDocumentNumber(request.getDocumentNumber());
                    return userDetailsRepository.save(newUserDetails);
                });
    }
}
