package ru.zubcov.flightbookingservice.userservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(length = 100, nullable = false, unique = true)
    private String email;
    @Column(nullable = false, length = 55)
    private String password;
}
