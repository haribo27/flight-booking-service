package ru.zubcov.flightbookingservice.apigatewayservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.UsersApi;
import org.openapitools.model.UserRequestDTO;
import org.openapitools.model.UserResponseDTO;
import org.openapitools.model.UserUpdateRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.zubcov.flightbookingservice.apigatewayservice.client.UserFeignClient;


@Controller
@Validated
@RequiredArgsConstructor
@Slf4j
public class GatewayUserController implements UsersApi {

    private final UserFeignClient userClient;

    @PostMapping("/users")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO request) {
        return userClient.saveUser(request);
    }

    @GetMapping("/users/{user_Id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable Long user_Id) {
        return userClient.findUserById(user_Id);
    }

    @PatchMapping("/users/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long userId,
                                                      @RequestBody @Valid UserUpdateRequestDTO request) {
        return userClient.updateUser(userId, request);
    }
}
