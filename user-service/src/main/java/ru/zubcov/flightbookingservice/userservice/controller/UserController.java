package ru.zubcov.flightbookingservice.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.zubcov.flightbookingservice.commondto.UserRequestDTO;
import ru.zubcov.flightbookingservice.commondto.UserResponseDTO;
import ru.zubcov.flightbookingservice.commondto.UserUpdateRequestDTO;
import ru.zubcov.flightbookingservice.userservice.service.UserService;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO request) {
        return new ResponseEntity<>(userService.saveUser(request), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable long userId) {
        return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserUpdateRequestDTO request, @PathVariable long userId) {
        return new ResponseEntity<>(userService.updateUser(request, userId), HttpStatus.OK);
    }
}
