package ru.zubcov.flightbookingservice.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zubcov.flightbookingservice.userservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
