package dev.vorstu.dto.mapping;

import dev.vorstu.dto.UserDto;
import dev.vorstu.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapping {

    UserDto toDto(User user);

}

