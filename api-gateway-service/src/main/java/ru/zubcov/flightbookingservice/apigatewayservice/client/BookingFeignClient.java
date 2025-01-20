package ru.zubcov.flightbookingservice.apigatewayservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.zubcov.flightbookingservice.commondto.BookingDTO;

import java.util.Set;

@FeignClient(name = "bookingClient", url = "${booking-service-url}")
public interface BookingFeignClient {

    @GetMapping(value = "/users/{userId}/bookings", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Set<BookingDTO>> getBookingInfo(@PathVariable long userId);
}
