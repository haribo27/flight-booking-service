package ru.zubcov.flightbookingservice.bookingservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.BookingDTO;
import org.openapitools.model.BookingRequestDTO;
import org.openapitools.model.BookingStatusUpdateDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zubcov.flightbookingservice.bookingservice.exception.EntityNotFound;
import ru.zubcov.flightbookingservice.bookingservice.mapper.BookingMapper;
import ru.zubcov.flightbookingservice.bookingservice.messaging.BookingMessageProducer;
import ru.zubcov.flightbookingservice.bookingservice.model.Booking;
import ru.zubcov.flightbookingservice.bookingservice.model.Flight;
import ru.zubcov.flightbookingservice.bookingservice.model.User;
import ru.zubcov.flightbookingservice.bookingservice.model.UserDetails;
import ru.zubcov.flightbookingservice.bookingservice.repository.BookingRepository;
import ru.zubcov.flightbookingservice.bookingservice.repository.FlightRepository;
import ru.zubcov.flightbookingservice.bookingservice.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import static org.openapitools.model.BookingDTO.BookingStatusEnum.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BookingServiceImpl implements BookingService {

    private final UserDetailsService userDetailsService;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final FlightSeatService flightSeatService;
    private final BookingMapper bookingMapper;
    private final BookingMessageProducer messageProducer;

    @Override
    @Transactional
    public void createBooking(BookingRequestDTO request) {
        Booking booking = new Booking();
        User user = userRepository.findById(request.getUserId().longValue()).orElseThrow();
        log.debug("Getting author of booking {}", user);
        booking.setUser(user);
        log.info("Set user to booking");
        UserDetails userDetails = userDetailsService.saveUserDetails(request, user);
        booking.setUserDetails(userDetails);
        log.debug("Setting userDetails to booking {}", userDetails);
        booking.setFlight(getBookingFlight(request));
        if (booking.getFlight() == null) {
            booking.setStatus(REJECTED_FLIGHT_NOT_FOUND);
            bookingRepository.save(booking);
            return;
        }
        log.info("Set flight to booking");
        String seatNumber = getBookingSeatNumber(request, booking);
        booking.setSeat(seatNumber);
        booking.setStatus(seatNumber == null ? REJECTED_NO_MORE_SEATS_ON_FLIGHT : AWAITING_CONFIRMATION);
        log.info("Booking status: {} from user", AWAITING_CONFIRMATION);

        bookingRepository.save(booking);
    }

    @Override
    public Set<BookingDTO> getBookingInfo(long userId) {
        log.info("Getting info about user's {} bookings", userId);
        return bookingRepository.findAllByUserId(userId).stream()
                .map(bookingMapper::mapToBookingDTO)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public void updateBookingStatus(BookingStatusUpdateDTO bookingStatusUpdateRequest) {
        log.info("Updating booking status {}", bookingStatusUpdateRequest);
        Booking booking = bookingRepository.findById(bookingStatusUpdateRequest.getBookingId().longValue())
                .orElseThrow(() ->
                        new EntityNotFound("Booking with id: " + bookingStatusUpdateRequest.getBookingId() + " not found"));
        if (booking.getStatus().equals(AWAITING_CONFIRMATION)) {
            if (bookingStatusUpdateRequest.getIsConfirmed()) {
                log.info("Changing booking status to {}", AWAITING_PAYMENT);
                booking.setStatus(AWAITING_PAYMENT);
            } else {
                log.info("Cancel booking and return seat at flight not reserved");
                booking.setStatus(CANCELLED);
                flightSeatService.cancelSeatReservation(booking.getFlight().getId(), booking.getSeat());
            }
            booking = bookingRepository.save(booking);
            log.info("Booking saved success {}", booking);
        }
    }

    private Flight getBookingFlight(BookingRequestDTO request) {
        log.debug("Getting flight info from user request");
        return flightRepository.findFlightByDepartureAirportAndArrivalAirportAndDepartureTime(
                        request.getDepartmentAirport(), request.getArrivalAirport(),
                        LocalDateTime.parse(request.getFlightDateTime()))
                .orElse(null);
    }

    private String getBookingSeatNumber(BookingRequestDTO request, Booking booking) {
        return flightSeatService.confirmFlightSeat(request.getSeatNumber(), booking.getFlight().getId());
    }
}
