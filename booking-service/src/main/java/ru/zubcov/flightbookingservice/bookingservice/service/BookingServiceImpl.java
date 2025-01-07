package ru.zubcov.flightbookingservice.bookingservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zubcov.flightbookingservice.bookingservice.mapper.BookingMapper;
import ru.zubcov.flightbookingservice.bookingservice.messaging.BookingMessageProducer;
import ru.zubcov.flightbookingservice.bookingservice.model.*;
import ru.zubcov.flightbookingservice.bookingservice.repository.BookingRepository;
import ru.zubcov.flightbookingservice.bookingservice.repository.FlightRepository;
import ru.zubcov.flightbookingservice.bookingservice.repository.UserRepository;
import ru.zubcov.flightbookingservice.commondto.BookingDTO;
import ru.zubcov.flightbookingservice.commondto.BookingRequestDTO;
import ru.zubcov.flightbookingservice.commondto.BookingStatusUpdateDTO;
import ru.zubcov.flightbookingservice.commondto.EntityNotFound;

import java.util.Set;
import java.util.stream.Collectors;

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
        User user = userRepository.findById(request.getUserId()).orElseThrow();
        log.debug("Getting author of booking {}", user);
        booking.setUser(user);
        log.info("Set user to booking");
        UserDetails userDetails = userDetailsService.saveUserDetails(request, user);
        booking.setUserDetails(userDetails);
        log.debug("Setting userDetails to booking {}", userDetails);
        booking.setFlight(getBookingFlight(request));
        if (booking.getFlight() == null) {
            booking.setStatus(BookingStatus.REJECTED_FLIGHT_NOT_FOUND);
            bookingRepository.save(booking);
            return;
        }
        log.info("Set flight to booking");
        String seatNumber = getBookingSeatNumber(request, booking);
        booking.setSeat(seatNumber);
        booking.setStatus(seatNumber == null ? BookingStatus.REJECTED_NO_MORE_SEATS_ON_FLIGHT : BookingStatus.AWAITING_CONFIRMATION);
        log.info("Booking status: {} from user", BookingStatus.AWAITING_CONFIRMATION);

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
        Booking booking = bookingRepository.findById(bookingStatusUpdateRequest.getBookingId())
                .orElseThrow(() ->
                        new EntityNotFound("Booking with id: " + bookingStatusUpdateRequest.getBookingId() + " not found"));
        if (booking.getStatus().equals(BookingStatus.AWAITING_CONFIRMATION)) {
            if (bookingStatusUpdateRequest.getIsConfirmed()) {
                log.info("Changing booking status to {}", BookingStatus.AWAITING_PAYMENT);
                booking.setStatus(BookingStatus.AWAITING_PAYMENT);
            } else {
                log.info("Cancel booking and return seat at flight not reserved");
                booking.setStatus(BookingStatus.CANCELLED);
                flightSeatService.cancelSeatReservation(booking.getFlight().getId(), booking.getSeat());
            }
            booking = bookingRepository.save(booking);
            log.info("Booking saved success {}", booking);
        }
    }

    private Flight getBookingFlight(BookingRequestDTO request) {
        log.debug("Getting flight info from user request");
        return flightRepository.findFlightByDepartureAirportAndArrivalAirportAndDepartureTime(
                        request.getDepartmentAirport(), request.getArrivalAirport(), request.getFlightDateTime())
                .orElse(null);
    }

    private String getBookingSeatNumber(BookingRequestDTO request, Booking booking) {
        return flightSeatService.confirmFlightSeat(request.getSeatNumber(), booking.getFlight().getId());
    }
}
