package ru.zubcov.flightbookingservice.userservice.service;


import org.openapitools.model.UserRequestDTO;
import org.openapitools.model.UserResponseDTO;
import org.openapitools.model.UserUpdateRequestDTO;

public interface UserService {
    UserResponseDTO saveUser(UserRequestDTO request);

    UserResponseDTO findById(long userId);

    UserResponseDTO updateUser(UserUpdateRequestDTO request, long userId);
}
