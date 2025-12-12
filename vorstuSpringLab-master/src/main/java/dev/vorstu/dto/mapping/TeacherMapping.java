package dev.vorstu.dto.mapping;

import dev.vorstu.dto.StudentDto;
import dev.vorstu.dto.TeacherDto;
import dev.vorstu.entity.Student;
import dev.vorstu.entity.Teacher;
import dev.vorstu.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeacherMapping {
    @Mapping(target = "phoneNumber", source = "teacher.phoneNumber")
    @Mapping(target = "groups", source = "teacher.groups")
    @Mapping(target = "id", source = "teacher.id")
    @Mapping(target = "fio", source = "teacher.fio")
    @Mapping(target = "username", source = "teacher.username")
    @Mapping(target = "role", source = "teacher.role")
    @Mapping(target = "enable", source = "teacher.enable")
    TeacherDto toDto(Teacher teacher);

    Teacher toEntity(TeacherDto teacherDto);

    Teacher updateTeacher(TeacherDto teacherDto, @MappingTarget Teacher teacher);

}
