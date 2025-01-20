package ru.zubcov.flightbookingservice.bookingservice.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.zubcov.flightbookingservice.bookingservice.model.Booking;

import java.util.Set;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @EntityGraph(value = "Booking.withAllObjects", type = EntityGraph.EntityGraphType.FETCH)
    Set<Booking> findAllByUserId(long userId);
}
