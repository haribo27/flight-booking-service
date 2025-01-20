package ru.zubcov.flightbookingservice.userservice.service;

import ru.zubcov.flightbookingservice.commondto.UserRequestDTO;
import ru.zubcov.flightbookingservice.commondto.UserResponseDTO;
import ru.zubcov.flightbookingservice.commondto.UserUpdateRequestDTO;

public interface UserService {
    UserResponseDTO saveUser(UserRequestDTO request);

    UserResponseDTO findById(long userId);

    UserResponseDTO updateUser(UserUpdateRequestDTO request, long userId);
}
