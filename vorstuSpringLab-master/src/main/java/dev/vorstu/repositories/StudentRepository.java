package dev.vorstu.repositories;

import dev.vorstu.dto.StudentDto;
import dev.vorstu.entity.Student;
import dev.vorstu.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    //todo многострочный текст
    @Query("SELECT s FROM Student s WHERE " +
            "s.group.groupName = :groupName AND " +
            "(s.fio LIKE CONCAT('%', :name, '%') OR " +
            "s.phoneNumber LIKE CONCAT('%', :name, '%'))")
    Page<StudentDto> findStudentsByNameContains(@Param("name") String name,
                                          @Param("groupName") String groupName,
                                          Pageable pageable);

    @Query("SELECT s FROM Student s WHERE " +
            "(s.fio LIKE CONCAT('%', :name, '%') OR " +
            "s.group.groupName LIKE CONCAT('%', :name, '%') OR " +
            "s.phoneNumber LIKE CONCAT('%', :name, '%'))")
    Page<StudentDto> findAllStudentsByNameContains(@Param("name") String name,
                                             Pageable pageable);

    @Query("SELECT s FROM Student s WHERE " +
            "s.group.groupName IN :groupNames AND " +
            "(s.fio LIKE CONCAT('%', :fio, '%') OR " +
            "s.phoneNumber LIKE CONCAT('%', :fio, '%'))")
    Page<StudentDto> findStudentsByTeacherGroups(
            @Param("groupNames") List<String> groupNames,
            @Param("fio") String fio,
            PageRequest pageRequest);

}
