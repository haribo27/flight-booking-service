package ru.zubcov.flightbookingservice.commondto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingStatusUpdateDTO {

    private Long userId;
    private Long bookingId;
    private Boolean isConfirmed;
}
