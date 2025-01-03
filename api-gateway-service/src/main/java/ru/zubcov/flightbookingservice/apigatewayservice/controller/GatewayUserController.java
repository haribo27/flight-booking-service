package ru.zubcov.flightbookingservice.apigatewayservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.zubcov.flightbookingservice.apigatewayservice.client.UserClient;
import ru.zubcov.flightbookingservice.commondto.UserRequestDTO;
import ru.zubcov.flightbookingservice.commondto.UserResponseDTO;
import ru.zubcov.flightbookingservice.commondto.UserUpdateRequestDTO;

@Controller
@Validated
@RequiredArgsConstructor
public class GatewayUserController {

    private final UserClient userClient;

    @PostMapping("/users")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO request) {
        return userClient.saveUser(request);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable long userId) {
        return userClient.findUserById(userId);
    }

    @PatchMapping("/users/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody @Valid UserUpdateRequestDTO request,
                                                      @PathVariable long userId) {
        return userClient.updateUser(request, userId);
    }
}
