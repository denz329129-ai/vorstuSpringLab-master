package dev.vorstu.dto.mapping;

import dev.vorstu.dto.AdminDto;
import dev.vorstu.dto.StudentDto;
import dev.vorstu.entity.Admin;
import dev.vorstu.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AdminMapping {

    AdminDto toDto(Admin admin);

    Admin updateAdmin(AdminDto adminDto, @MappingTarget Admin admin);
}
