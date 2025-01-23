package ru.zubcov.flightbookingservice.apigatewayservice.client;

import org.openapitools.model.BookingDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "bookingClient", url = "${booking-service-url}")
public interface BookingFeignClient {

    @GetMapping(value = "/bookings/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<BookingDTO>> getBookingInfo(@PathVariable long userId);
}
