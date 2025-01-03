package ru.zubcov.flightbookingservice.apigatewayservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.zubcov.flightbookingservice.apigatewayservice.client.BookingClient;
import ru.zubcov.flightbookingservice.commondto.BookingRequestDTO;

@Controller
@Validated
@RequiredArgsConstructor
public class GatewayBookingController {

    private final BookingClient bookingClient;

    @PostMapping("users/{userId}/booking")
    public ResponseEntity<BookingRequestDTO> createBookingRequest(@RequestBody @Valid BookingRequestDTO request,
                                                                  @PathVariable long userId) {
        return new ResponseEntity<>(bookingClient.handleAndSendBookingRequest(request, userId),
                HttpStatus.CREATED);
    }
}
