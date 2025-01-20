package ru.zubcov.flightbookingservice.commondto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class UserRequestDTO {

    @NotBlank(message = "Поле логин не может быть пустым")
    @Size(min = 6, max = 255, message = "Длина логина должна быть от 6 до 255 символов")
    String login;
    @NotBlank(message = "Поле почта не должно быть пустым")
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",
            message = "Не верный формат почты")
    @Size(min = 4, max = 100, message = "Длина почта должны быть от 4 до 100 символов")
    String email;
    @NotBlank(message = "Поле пароль не может быть пустым")
    @Size(min = 3, max = 55, message = "Длина пароля должна быть от 3х до 55 символов")
    String password;
}
