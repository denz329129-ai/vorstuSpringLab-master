package dev.vorstu.repositories;

import dev.vorstu.entity.*;
import dev.vorstu.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Initializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GroupRepository groupRepository;

    public void initial() {
        Group bvm231 = new Group("BVM-231");
        Group bvm232 = new Group("BVM-232");
        Group bvm233 = new Group("BVM-233");
        groupRepository.saveAll(Arrays.asList(bvm231, bvm232, bvm233));

        List<Student> students = Arrays.asList(
                // Студенты группы BVM-231 (10 человек)
                new Student(null, "student1", Role.STUDENT, true, new Password("student1"), "Ivanov Alex", "+79161234567", bvm231),
                new Student(null, "student2", Role.STUDENT, true, new Password("student2"), "Petrov Mike", "+79161234568", bvm231),
                new Student(null, "student3", Role.STUDENT, true, new Password("student3"), "Sidorov John", "+79161234569", bvm231),
                new Student(null, "student4", Role.STUDENT, true, new Password("student4"), "Kuznetsov Anna", "+79161234570", bvm231),
                new Student(null, "student5", Role.STUDENT, true, new Password("student5"), "Popov Maria", "+79161234571", bvm231),
                new Student(null, "student6", Role.STUDENT, true, new Password("student6"), "Lebedev Olga", "+79161234572", bvm231),
                new Student(null, "student7", Role.STUDENT, true, new Password("student7"), "Kozlov Steve", "+79161234573", bvm231),
                new Student(null, "student8", Role.STUDENT, true, new Password("student8"), "Novikov Irina", "+79161234574", bvm231),
                new Student(null, "student9", Role.STUDENT, true, new Password("student9"), "Morozov Tom", "+79161234575", bvm231),
                new Student(null, "student10", Role.STUDENT, true, new Password("student10"), "Volkov Natalia", "+79161234576", bvm231),

                // Студенты группы BVM-232 (5 человек)
                new Student(null, "student11", Role.STUDENT, true, new Password("student11"), "Fedorov Alexey", "+79161234577", bvm232),
                new Student(null, "student12", Role.STUDENT, true, new Password("student12"), "Orlov Dmitry", "+79161234578", bvm232),
                new Student(null, "student13", Role.STUDENT, true, new Password("student13"), "Belyaev Sergey", "+79161234579", bvm232),
                new Student(null, "student14", Role.STUDENT, true, new Password("student14"), "Gusev Andrey", "+79161234580", bvm232),
                new Student(null, "student15", Role.STUDENT, true, new Password("student15"), "Titov Pavel", "+79161234581", bvm232),

                // Студенты группы BVM-233 (15 человек)
                new Student(null, "student16", Role.STUDENT, true, new Password("student16"), "Komarov Elena", "+79161234582", bvm233),
                new Student(null, "student17", Role.STUDENT, true, new Password("student17"), "Shcherbakov Kate", "+79161234583", bvm233),
                new Student(null, "student18", Role.STUDENT, true, new Password("student18"), "Mikhailov Julia", "+79161234584", bvm233),
                new Student(null, "student19", Role.STUDENT, true, new Password("student19"), "Romanov Victoria", "+79161234585", bvm233),
                new Student(null, "student20", Role.STUDENT, true, new Password("student20"), "Vasiliev Sophia", "+79161234586", bvm233),
                new Student(null, "student21", Role.STUDENT, true, new Password("student21"), "Zaitsev Artem", "+79161234587", bvm233),
                new Student(null, "student22", Role.STUDENT, true, new Password("student22"), "Pavlov Maxim", "+79161234588", bvm233),
                new Student(null, "student23", Role.STUDENT, true, new Password("student23"), "Semyonov Ivan", "+79161234589", bvm233),
                new Student(null, "student24", Role.STUDENT, true, new Password("student24"), "Golubev Roman", "+79161234590", bvm233),
                new Student(null, "student25", Role.STUDENT, true, new Password("student25"), "Vinogradov Denis", "+79161234591", bvm233),
                new Student(null, "student26", Role.STUDENT, true, new Password("student26"), "Bogdanov Kristina", "+79161234592", bvm233),
                new Student(null, "student27", Role.STUDENT, true, new Password("student27"), "Vorobiev Alina", "+79161234593", bvm233),
                new Student(null, "student28", Role.STUDENT, true, new Password("student28"), "Filippov Daria", "+79161234594", bvm233),
                new Student(null, "student29", Role.STUDENT, true, new Password("student29"), "Konstantinov Polina", "+79161234595", bvm233),
                new Student(null, "student30", Role.STUDENT, true, new Password("student30"), "Grigoriev Angelina", "+79161234596", bvm233)
        );

        for (Student student : students) {
            studentRepository.save(student);
        }

        // Основные пользователи с разными ролями
        Student basicStudent = new Student(
                null,
                "student",
                Role.STUDENT,
                true,
                new Password("student"),
                "Igor Gofman Student",
                "+79160000001",
                bvm231
        );
        studentRepository.save(basicStudent);

        Admin admin = new Admin(
                null,
                "admin",
                Role.ADMIN,
                true,
                new Password("admin")
        );
        userRepository.save(admin);

        Teacher teacher = new Teacher(
                null,
                "teacher",
                Role.TEACHER,
                true,
                new Password("teacher"),
                "Igor Gofman Teacher",
                "+79160000003",
                Arrays.asList(bvm231, bvm232)
        );
        userRepository.save(teacher);

        Teacher teacher1 = new Teacher(
                null,
                "teacher1",
                Role.TEACHER,
                true,
                new Password("teacher"),
                "Igor Gofman Teacher1",
                "+79160000003",
                Arrays.asList(bvm233, bvm232)
        );
        userRepository.save(teacher1);

        Teacher teacher2 = new Teacher(
                null,
                "teacher2",
                Role.TEACHER,
                true,
                new Password("teacher2"),
                "Igor Gofman Teacher",
                "+79160000003",
                Arrays.asList(bvm231, bvm233)
        );
        userRepository.save(teacher2);
    }
}