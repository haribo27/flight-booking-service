package ru.zubcov.flightbookingservice.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.UsersApi;
import org.openapitools.model.UserRequestDTO;
import org.openapitools.model.UserResponseDTO;
import org.openapitools.model.UserUpdateRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.zubcov.flightbookingservice.userservice.metrics.UserMetricsService;
import ru.zubcov.flightbookingservice.userservice.service.UserService;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController implements UsersApi {

    private final UserService userService;
    private final UserMetricsService metricsService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO request) {
        metricsService.IncrementUserRequestsCounter();
        return new ResponseEntity<>(userService.saveUser(request), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable long userId) {
        metricsService.IncrementUserRequestsCounter();
        return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserUpdateRequestDTO request, @PathVariable long userId) {
        metricsService.IncrementUserRequestsCounter();
        return new ResponseEntity<>(userService.updateUser(request, userId), HttpStatus.OK);
    }
}
