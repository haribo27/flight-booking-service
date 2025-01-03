package ru.zubcov.flightbookingservice.userservice.mapper;

import org.mapstruct.*;
import ru.zubcov.flightbookingservice.commondto.UserRequestDTO;
import ru.zubcov.flightbookingservice.commondto.UserResponseDTO;
import ru.zubcov.flightbookingservice.commondto.UserUpdateRequestDTO;
import ru.zubcov.flightbookingservice.userservice.model.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User mapToUser(UserRequestDTO requestDTO);

    UserResponseDTO mapToUserResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(UserUpdateRequestDTO request, @MappingTarget User user);
}
