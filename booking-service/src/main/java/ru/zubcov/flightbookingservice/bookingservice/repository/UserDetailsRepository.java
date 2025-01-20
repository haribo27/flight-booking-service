package ru.zubcov.flightbookingservice.bookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zubcov.flightbookingservice.bookingservice.model.UserDetails;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    Optional<UserDetails> findByDocumentNumber(String documentNumber);
}
