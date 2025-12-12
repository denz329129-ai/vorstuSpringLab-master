package dev.vorstu.services;

import dev.vorstu.dto.*;
import dev.vorstu.dto.mapping.AdminMapping;
import dev.vorstu.dto.mapping.GroupMapping;
import dev.vorstu.dto.mapping.StudentMapping;
import dev.vorstu.dto.mapping.TeacherMapping;
import dev.vorstu.entity.Admin;
import dev.vorstu.entity.Group;
import dev.vorstu.entity.Student;
import dev.vorstu.entity.User;
import dev.vorstu.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final StudentRepository studentRepository;
    private final StudentMapping studentMapping;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final AdminMapping adminMapping;
    private final GroupMapping groupMapping;
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;

    public Page<StudentDto> getStudentsWithPagination(String name, PageRequest pageRequest) {
        return studentRepository.findAllStudentsByNameContains(name, pageRequest);
    }

    //todo @validated - spring валидация
    public void deleteStudent(Long id){
        if (id == null) {
            throw new RuntimeException("Student id is null");
        }
        userRepository.deleteById(id);
    }

    public void changeStudent(StudentDto student){
        if (student.getId() == null){
            throw new RuntimeException("User id is null");
        }
        //todo findById is it necessary
        Student changingStudent = studentRepository.findById(student.getId()).orElseThrow(() -> new RuntimeException("Student not found"));

        studentMapping.updateStudent(student, changingStudent);

        studentRepository.save(changingStudent);

    }


    public AdminDto getCurrentUser(Long id) {

        Admin admin = adminRepository.findById(id).orElse(null);

        return adminMapping.toDto(admin);
    }

    public void createNewGroup(GroupDto group){
        groupRepository.save(groupMapping.toEntity(group));
    }

    public void changeGroup(GroupDto group){
        //todo ??
        Group changingGroup = groupRepository.findById(group.getId()).orElseThrow(() -> new RuntimeException("Group not found"));

        groupMapping.updateGroup(group, changingGroup);

        groupRepository.save(changingGroup);

    }

    public void changeAdmin(AdminDto admin){
        //todo ?
        Admin changingAdmin = adminRepository.findById(admin.getId())
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        adminMapping.updateAdmin(admin, changingAdmin);
        adminRepository.save(changingAdmin);
    }

    public void deleteGroup(Long id){
        groupRepository.deleteById(id);
    }

    public void deleteTeacher(Long Id){
        teacherRepository.deleteById(Id);
    }
}
