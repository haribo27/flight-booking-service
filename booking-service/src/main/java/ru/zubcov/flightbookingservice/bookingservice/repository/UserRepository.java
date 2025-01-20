package ru.zubcov.flightbookingservice.bookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zubcov.flightbookingservice.bookingservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
