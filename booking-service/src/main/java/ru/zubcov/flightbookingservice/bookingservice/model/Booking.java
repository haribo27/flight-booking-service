package ru.zubcov.flightbookingservice.bookingservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.model.BookingDTO;

@NamedEntityGraph(
        name = "Booking.withAllObjects",
        attributeNodes = {
                @NamedAttributeNode("user"),
                @NamedAttributeNode("userDetails"),
                @NamedAttributeNode("flight"),
        }
)
@Getter
@Setter
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_details_id")
    private UserDetails userDetails;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight flight;
    @Column(nullable = false)
    private String seat;
    @Enumerated(EnumType.STRING)
    private BookingDTO.BookingStatusEnum status;
}
