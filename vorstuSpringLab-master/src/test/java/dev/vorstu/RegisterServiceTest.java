package dev.vorstu;


import dev.vorstu.entity.Student;
import dev.vorstu.repositories.StudentRepository;
import dev.vorstu.repositories.TeacherRepository;
import dev.vorstu.services.RegisterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegisterServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private RegisterService registerService;

    @Test
    void registerTest(){
//        //arrange
//        Student student = new Student();
//        when(studentRepository.save(student)).thenReturn(student);
//        //act
//
//        Student result = studentRepository.save(student);
//
//        //assert
//        assertTrue(result.isPresent());

    }
}
