package dev.vorstu;

import dev.vorstu.dto.StudentDto;
import dev.vorstu.dto.mapping.StudentMapping;
import dev.vorstu.entity.Group;
import dev.vorstu.entity.Student;
import dev.vorstu.enums.Role;
import dev.vorstu.repositories.StudentRepository;
import dev.vorstu.services.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapping studentMapping;

    @InjectMocks
    private StudentService studentService;

    private Student student;
    private StudentDto studentDto;
    private PageRequest pageRequest;

    @BeforeEach
    void setUp() {

        pageRequest = PageRequest.of(0, 10);

        Group group = new Group(152L, "testGroup");

        student = new Student();
        student.setId(1L);
        student.setUsername("test");
        student.setGroup(group);

        studentDto = new StudentDto(1L, "test", Role.STUDENT, "test", "123", group, true);
        studentDto.setId(1L);
        studentDto.setUsername("test");
    }

    @Test
    void getStudentsWithPaginationTest_WhenStudentExists_ReturnPage(){
        //arrange
        Long studentId = 1L;
        String searchName = "test";

        when(studentRepository.findById(studentId))
                .thenReturn(Optional.of(student));

        Page<StudentDto> expectedPage = new PageImpl<>(Collections.singletonList(studentDto));

        when(studentRepository.findStudentsByNameContains(anyString(), anyString(), any(PageRequest.class)))
                .thenReturn(expectedPage);

        //act

        Page<StudentDto> result = studentService.getStudentsWithPagination(studentId, searchName, pageRequest);

        //assert

        assertNotNull(result, "Результат не должен быть null");
        assertEquals(1, result.getTotalElements(), "Должен вернуться 1 элемент");

        verify(studentRepository).findById(studentId);

        verify(studentRepository).findStudentsByNameContains(
                searchName,
                student.getGroup().getGroupName(),
                pageRequest
        );
    }

    @Test
    void getStudentsWithPaginationTest_WhenStudentDoesNotExist_ReturnPage(){
        Long studentId = 1L;
        String searchName = "test";

//        when(studentRepository.findById(studentId)).thenReturn(RuntimeException)
    }




}
