package dev.vorstu.dto.mapping;

import dev.vorstu.dto.GroupDto;
import dev.vorstu.entity.Group;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GroupMapping {


    GroupDto toDto(Group group);

    Group toEntity(GroupDto groupDto);

    Group updateGroup(GroupDto groupDto, @MappingTarget Group group);
}