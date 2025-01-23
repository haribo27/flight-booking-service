package ru.zubcov.flightbookingservice.bookingservice.controller;

import lombok.RequiredArgsConstructor;
import org.openapitools.model.BookingDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.zubcov.flightbookingservice.bookingservice.service.BookingService;

import java.util.Set;

@Controller
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/bookings/users/{userId}")
    public ResponseEntity<Set<BookingDTO>> getBookingInfo(@PathVariable long userId) {
        return new ResponseEntity<>(bookingService.getBookingInfo(userId),
                HttpStatus.OK);
    }
}
