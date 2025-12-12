package dev.vorstu.services;

import dev.vorstu.dto.*;
import dev.vorstu.dto.mapping.StudentMapping;
import dev.vorstu.entity.*;
import dev.vorstu.enums.Role;
import dev.vorstu.repositories.GroupRepository;
import dev.vorstu.repositories.StudentRepository;
import dev.vorstu.repositories.TeacherRepository;
import dev.vorstu.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final StudentMapping studentMapping;

    public void register(RegisterRequest user){
        Student student = studentMapping.toEntity(user);

        //todo сделать без запроса группы
        student.setGroup(groupRepository.getById((long) user.getGroupId()));
        studentRepository.save(student);
    }

    public void registerTeacher(RegisterRequest user){
        List<Group> group = new ArrayList<>();

        Teacher teacher = new Teacher(
                null,
                user.getUsername(),
                Role.TEACHER,
                true,
                user.getPassword(),
                user.getFio(),
                user.getPhoneNumber(),
                group

        );
        teacherRepository.save(teacher);
    }

}