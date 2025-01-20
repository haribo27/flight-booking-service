package ru.zubcov.flightbookingservice.bookingservice.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.zubcov.flightbookingservice.bookingservice.model.Flight;

import java.time.LocalDateTime;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @EntityGraph(value = "Flight.withAircraft", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Flight> findFlightByDepartureAirportAndArrivalAirportAndDepartureTime(
            String departureAirport, String arrivalAirport, LocalDateTime departureTime);
}
