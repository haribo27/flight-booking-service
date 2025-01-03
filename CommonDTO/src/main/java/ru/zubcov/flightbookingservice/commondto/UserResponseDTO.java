package ru.zubcov.flightbookingservice.commondto;

import lombok.Value;

@Value
public class UserResponseDTO {

    Long id;
    String login;
    String email;
    String password;
}
