package ru.zubcov.flightbookingservice.bookingservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@NamedEntityGraph(
        name = "Flight.withAircraft",
        attributeNodes = {
                @NamedAttributeNode("aircraft")
        }
)
@Getter
@Setter
@Entity
@Table(name = "flights_schedule")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "flight_number", nullable = false, unique = true, length = 10)
    private String flightNumber;
    @Column(name = "departure_airport", nullable = false, length = 50)
    private String departureAirport;
    @Column(name = "arrival_airport", nullable = false, length = 50)
    private String arrivalAirport;
    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;
    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aircraft_id", nullable = false)
    private Fleet aircraft;
}
