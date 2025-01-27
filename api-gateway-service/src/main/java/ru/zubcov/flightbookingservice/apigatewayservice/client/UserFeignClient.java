package ru.zubcov.flightbookingservice.apigatewayservice.client;

import org.openapitools.model.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.zubcov.flightbookingservice.apigatewayservice.config.FeignConfiguration;


@FeignClient(name = "userClient", url = "${user-service-url}", configuration = FeignConfiguration.class)
public interface UserFeignClient {

    @GetMapping(value = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserResponseDTO> findUserById(@PathVariable long userId);
}
