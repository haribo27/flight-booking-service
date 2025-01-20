package ru.zubcov.flightbookingservice.commondto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO для запроса на бронирование")
public class BookingRequestDTO {

    @NotBlank(message = "Поле Имя не может быть пустым")
    @Size(min = 1, max = 50,
            message = "Длина имени должна быть в пределах от 1 до 50 символов")
    @Schema(description = "Имя пассажира")
    String name;
    @NotBlank(message = "Поле Фамилия не может быть пустым")
    @Size(min = 1, max = 50,
            message = "Длина фамилии должна быть в пределах от 1 до 50 символов")
    @Schema(description = "Фамилия пассажира")
    String surname;
    @Size(min = 1, max = 50,
            message = "Длина отчества должна быть в пределах от 1 до 50 символов")
    @Schema(description = "Отчество пассажира")
    String patronymic;
    @NotBlank(message = "Поле Имя не может быть пустым")
    @Size(min = 10, max = 10,
            message = "Длина номера паспорта должна быть 10 символов")
    @Schema(description = "Номер документа")
    String documentNumber;
    @NotBlank(message = "Город вылета не может быть пустым")
    @Size(min = 1, max = 50,
            message = "Длина поля города вылета должно быть в пределах от 1 до 50 символов")
    @Schema(description = "Аэропорт отправления")
    String departmentAirport;
    @NotBlank(message = "Город прибытия не может быть пустым")
    @Size(min = 1, max = 50,
            message = "Длина поля города прибытия должно быть в пределах от 1 до 50 символов")
    @Schema(description = "Аэропорт прибытия")
    String arrivalAirport;
    @NotNull(message = "Время вылета не может быть пустым")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Время вылета")
    LocalDateTime flightDateTime;
    @Pattern(regexp = "^[1-9]([0-9])?[A-Fa-f]$",
            message = "Формат ввода номера места должен быть (1-99) Номер места, (A-F), номер ряда. 25B")
    @Schema(description = "Место в самолете")
    String seatNumber;
    @Schema(description = "Идентификатор пользователя, который создает бронирование")
    Long userId;
}
