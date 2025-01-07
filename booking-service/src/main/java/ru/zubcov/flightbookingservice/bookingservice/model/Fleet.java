package ru.zubcov.flightbookingservice.bookingservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "fleets")
public class Fleet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 20)
    private String registration;
    @Column(nullable = false, length = 20)
    private String model;
    @Column(nullable = false)
    private Integer capacity;

}
