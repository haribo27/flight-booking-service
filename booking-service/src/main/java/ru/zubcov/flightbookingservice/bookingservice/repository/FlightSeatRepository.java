package ru.zubcov.flightbookingservice.bookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zubcov.flightbookingservice.bookingservice.model.FlightSeat;

import java.util.List;
import java.util.Optional;

public interface FlightSeatRepository extends JpaRepository<FlightSeat, Long> {

    List<FlightSeat> findByFlight_IdAndIsReservedFalse(long flightId);

    Optional<FlightSeat> findByFlight_IdAndSeatNumber(long flightId, String seatNumber);
}
