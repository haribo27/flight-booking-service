package ru.zubcov.flightbookingservice.bookingservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    @Column(name = "second_name", nullable = false, length = 50)
    private String surName;
    @Column(name = "patronymic", length = 50)
    private String patronymic;
    @Column(name = "document_number",length = 10, unique = true, nullable = false)
    private String documentNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
