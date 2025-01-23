package ru.zubcov.flightbookingservice.apigatewayservice.client;

import org.openapitools.model.UserRequestDTO;
import org.openapitools.model.UserResponseDTO;
import org.openapitools.model.UserUpdateRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.zubcov.flightbookingservice.apigatewayservice.config.FeignConfiguration;


@FeignClient(name = "userClient", url = "${user-service-url}", configuration = FeignConfiguration.class)
public interface UserFeignClient {

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequestDTO request);

    @GetMapping(value = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserResponseDTO> findUserById(@PathVariable long userId);

    @PatchMapping(value = "/users/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserResponseDTO> updateUser(@PathVariable long userId,
                                               @RequestBody UserUpdateRequestDTO request);
}
