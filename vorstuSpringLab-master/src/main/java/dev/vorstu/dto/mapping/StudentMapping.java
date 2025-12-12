package dev.vorstu.dto.mapping;

import dev.vorstu.dto.RegisterRequest;
import dev.vorstu.dto.StudentDto;
import dev.vorstu.entity.Student;
import dev.vorstu.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapping {
    @Mapping(target = "phoneNumber", expression = "java(student.getPhoneNumber())")
    @Mapping(target = "group", expression = "java(student.getGroup())")
    @Mapping(target = "id", expression = "java(student.getId())")
    @Mapping(target = "fio", expression = "java(student.getFio())")
    @Mapping(target = "username", expression = "java(student.getUsername())")
    @Mapping(target = "role", expression = "java(student.getRole())")
    @Mapping(target = "enable", expression = "java(student.getEnable())")
    StudentDto toDto(Student student);

    Student updateStudent(StudentDto studentDto, @MappingTarget Student student);


    Student toEntity(RegisterRequest registerRequest);
}
