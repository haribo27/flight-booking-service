package ru.zubcov.flightbookingservice.bookingservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seats")
public class FlightSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight flight;
    @Column(name = "seat_number", nullable = false)
    private String seatNumber;
    @Column(name = "is_reserved",nullable = false)
    private Boolean isReserved;
}
