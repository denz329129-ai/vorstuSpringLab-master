package dev.vorstu.services;

import dev.vorstu.dto.StudentDto;
import dev.vorstu.dto.TeacherDto;
import dev.vorstu.dto.mapping.StudentMapping;
import dev.vorstu.dto.mapping.TeacherMapping;
import dev.vorstu.entity.Group;
import dev.vorstu.entity.Student;
import dev.vorstu.entity.Teacher;
import dev.vorstu.entity.User;
import dev.vorstu.repositories.StudentRepository;
import dev.vorstu.repositories.TeacherRepository;
import dev.vorstu.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {


    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;

    private final TeacherMapping teacherMapping;
    private final StudentMapping studentMapping;

    public Page<StudentDto> getStudentsWithPagination(Long id, String name, PageRequest pageRequest) {
        Teacher currentTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        //todo custom exception NotFoundException, ControllerAdvice ??

        List<String> groupNames = currentTeacher.getGroups().stream()
                .map(Group::getGroupName)
                .collect(Collectors.toList());

        //todo лишний запрос на групп нэйм, сделать фильтр по join от студетнтов->group->teacher
        return studentRepository.findStudentsByTeacherGroups(groupNames, name, pageRequest);

    }

    public Page<TeacherDto> getTeachersWithPagination(String name, PageRequest pageRequest) {
        Page<Teacher> teachers = teacherRepository.findTeachersWithPagination(name, pageRequest);
        return teachers.map(teacherMapping::toDto);
    }

    public void deleteStudent(Long id){
        if (id == null) {
            throw new RuntimeException("Student id is null");
        }
        //todo spring validation
        userRepository.deleteById(id);
    }

    public void changeStudent(StudentDto student){
        if (student.getId() == null){
            throw new RuntimeException("User id is null");
        }

        Student changingStudent = studentRepository.findById(student.getId()).orElseThrow(() -> new RuntimeException("Student not found"));

        studentMapping.updateStudent(student, changingStudent);

        studentRepository.save(changingStudent);

    }

    public void changeTeacher(TeacherDto teacher){

        Teacher changingTeacher = teacherRepository.findById(teacher.getId()).orElseThrow(() -> new RuntimeException("Teacher not found"));

        teacherMapping.updateTeacher(teacher, changingTeacher);

        teacherRepository.save(changingTeacher);
    }

    public TeacherDto getCurrentUser(Long id) {

        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));

        return teacherMapping.toDto(teacher);
    }


}
