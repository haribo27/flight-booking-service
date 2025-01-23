package ru.zubcov.flightbookingservice.bookingservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zubcov.flightbookingservice.bookingservice.exception.EntityNotFound;
import ru.zubcov.flightbookingservice.bookingservice.model.FlightSeat;
import ru.zubcov.flightbookingservice.bookingservice.repository.FlightSeatRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class FlightSeatServiceImpl implements FlightSeatService {

    private final FlightSeatRepository flightSeatRepository;

    @Override
    @Transactional
    public String confirmFlightSeat(String seatNumber, Long flightId) {
        // Получаем все свободные места на указанный рейс
        List<FlightSeat> seats = flightSeatRepository.
                findByFlight_IdAndIsReservedFalse(flightId);
        // Делаем мапу, где ключ место, а значение  объект места в самолете,
        // чтобы можно было быстрее искать места
        Map<String, FlightSeat> flightSeatMap = seats.stream()
                .collect(Collectors.toMap(
                        FlightSeat::getSeatNumber,
                        seat -> seat
                ));
        if (flightSeatMap.isEmpty()) {
            // если мапа пустая, мест нет на самолет.
            return null;
        }
        FlightSeat bookingFlightSeat = flightSeatMap.get(seatNumber);
        if (bookingFlightSeat != null) {
            // если место есть, броним место, сохраняем в бд
            bookingFlightSeat.setIsReserved(true);
            flightSeatRepository.save(bookingFlightSeat);
            return seatNumber;
        } else if (!flightSeatMap.isEmpty()) {
            // если выбранное место уже занято, выбираем рандомное свободное место
            return getAndReturnRandomFlightSeat(flightSeatMap);
        }
        if (seatNumber == null || seatNumber.isBlank() || seatNumber.isEmpty()) {
            // если место в бронировании не указано, то рандомно выбираем место
            return getAndReturnRandomFlightSeat(flightSeatMap);
        }
        return seatNumber;
    }

    @Override
    @Transactional
    public void cancelSeatReservation(long flightId, String seatNumber) {
        FlightSeat flightSeat = flightSeatRepository.
                findByFlight_IdAndSeatNumber(flightId, seatNumber).orElseThrow(() -> new EntityNotFound("Flight seat not found"));
        flightSeat.setIsReserved(false);
        flightSeatRepository.save(flightSeat);
    }

    private String getAndReturnRandomFlightSeat(Map<String, FlightSeat> flightSeatMap) {
        FlightSeat bookingFlightSeatRandom = flightSeatMap.values().stream().findAny().get();
        bookingFlightSeatRandom.setIsReserved(true);
        flightSeatRepository.save(bookingFlightSeatRandom);
        return bookingFlightSeatRandom.getSeatNumber();
    }
}
