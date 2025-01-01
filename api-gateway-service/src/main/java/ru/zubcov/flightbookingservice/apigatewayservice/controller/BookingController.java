package ru.zubcov.flightbookingservice.apigatewayservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zubcov.flightbookingservice.apigatewayservice.service.ApiGatewayService;
import ru.zubcov.flightbookingservice.commondto.BookingRequestDTO;
import ru.zubcov.flightbookingservice.commondto.BookingResponseDTO;

@RestController
@RequestMapping("/booking")
@Validated
@RequiredArgsConstructor
public class BookingController {

    private final ApiGatewayService apiGatewayService;

    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody @Valid BookingRequestDTO request) {
        return new ResponseEntity<>(apiGatewayService.handleAndSendBookingRequest(request),
                HttpStatus.CREATED);
    }
}
