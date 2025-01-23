package ru.zubcov.flightbookingservice.userservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.UserRequestDTO;
import org.openapitools.model.UserResponseDTO;
import org.openapitools.model.UserUpdateRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zubcov.flightbookingservice.userservice.exception.EntityNotFound;
import ru.zubcov.flightbookingservice.userservice.mapper.UserMapper;
import ru.zubcov.flightbookingservice.userservice.model.User;
import ru.zubcov.flightbookingservice.userservice.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    @Transactional
    public UserResponseDTO saveUser(UserRequestDTO request) {
        log.debug("Mapping user request to user {}", request);
        User user = mapper.mapToUser(request);
        user = userRepository.save(user);
        log.info("User saved: {}", user);
        return mapper.mapToUserResponse(user);
    }

    @Override
    public UserResponseDTO findById(long userId) {
        log.info("Finding user by id: {}", userId);
        return userRepository.findById(userId)
                .map(mapper::mapToUserResponse)
                .orElseThrow(() -> new EntityNotFound("User with id: " + " not found"));
    }


    @Override
    @Transactional
    public UserResponseDTO updateUser(UserUpdateRequestDTO request, long userId) {
        log.info("Updating user with id: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFound("User with id: " + " not found"));
        mapper.updateUser(request, user);
        user = userRepository.save(user);
        log.info("Updated user: {}", user);
        return mapper.mapToUserResponse(user);
    }
}
