package ru.zubcov.flightbookingservice.commondto;

import jakarta.validation.constraints.Pattern;
import lombok.Value;

@Value
public class UserUpdateRequestDTO {

    String login;
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",
            message = "Не верный формат почты")
    String email;
    String password;
}
