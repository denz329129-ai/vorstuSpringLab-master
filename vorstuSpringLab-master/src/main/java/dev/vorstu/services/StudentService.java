package dev.vorstu.services;

import dev.vorstu.dto.StudentDto;
import dev.vorstu.dto.mapping.StudentMapping;
import dev.vorstu.entity.Student;
import dev.vorstu.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapping studentMapping;

    public Page<StudentDto> getStudentsWithPagination(Long id, String name, PageRequest pageRequest) {

        Student currentStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        String groupName = currentStudent.getGroup().getGroupName();

        //todo filter only by groupId
        return studentRepository.findStudentsByNameContains(name, groupName, pageRequest);

    }

    public void changeStudent(StudentDto student){
        //todo spring validation
        if (student.getId() == null){
            throw new RuntimeException("User id is null");
        }
        //todo throw exception
        Student changingStudent = studentRepository.findById(student.getId()).orElse(null);

        studentMapping.updateStudent(student, changingStudent);

        studentRepository.save(changingStudent);
    }

    public StudentDto getCurrentStudent(Long id) {

        Student student = studentRepository.findById(id).orElse(null);

        return studentMapping.toDto(student);
    }

}