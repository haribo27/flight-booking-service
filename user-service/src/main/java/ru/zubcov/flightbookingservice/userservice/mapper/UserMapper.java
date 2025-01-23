package ru.zubcov.flightbookingservice.userservice.mapper;

import org.mapstruct.*;
import org.openapitools.model.UserRequestDTO;
import org.openapitools.model.UserResponseDTO;
import org.openapitools.model.UserUpdateRequestDTO;
import ru.zubcov.flightbookingservice.userservice.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User mapToUser(UserRequestDTO requestDTO);

    UserResponseDTO mapToUserResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(UserUpdateRequestDTO request, @MappingTarget User user);
}
