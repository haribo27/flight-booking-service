package ru.zubcov.flightbookingservice.apigatewayservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zubcov.flightbookingservice.apigatewayservice.config.FeignConfiguration;
import ru.zubcov.flightbookingservice.commondto.UserRequestDTO;
import ru.zubcov.flightbookingservice.commondto.UserResponseDTO;
import ru.zubcov.flightbookingservice.commondto.UserUpdateRequestDTO;

@FeignClient(name = "userClient", url = "${user-service-url}", configuration = FeignConfiguration.class)
public interface UserFeignClient {

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequestDTO request);

    @GetMapping(value = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserResponseDTO> findUserById(@PathVariable long userId);

    @PatchMapping(value = "/users/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserUpdateRequestDTO request,
                                               @PathVariable long userId);
}
