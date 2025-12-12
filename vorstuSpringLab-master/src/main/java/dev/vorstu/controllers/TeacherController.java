package dev.vorstu.controllers;

import dev.vorstu.dto.AdminDto;
import dev.vorstu.dto.StudentDto;
import dev.vorstu.dto.TeacherDto;
import dev.vorstu.entity.Teacher;
import dev.vorstu.jwt.JwtAuthentication;
import dev.vorstu.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/teacher")

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/{id}/{page}/{size}")
    public Page<StudentDto> getStudentsWithPagination(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size") int size,

            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(name = "sort", defaultValue = "id,asc") String sort){

            String[] parts = sort.split(",");
            String field = parts[0];
            Sort.Direction direction = Sort.Direction.fromString(parts[1].toUpperCase());
            if (field.equals("group")) {
                field = "group.groupName";
            }

            PageRequest pageRequeest = PageRequest.of(page, size, Sort.by(direction, field));

        return teacherService.getStudentsWithPagination(id, name, pageRequeest);
    }

    @GetMapping("me")
    public TeacherDto getCurrentUser(JwtAuthentication authentication) {
        return teacherService.getCurrentUser(authentication.getUserId());
    }


    @PutMapping(value = "me", produces = MediaType.APPLICATION_JSON_VALUE)
    public void changeTeacher(@RequestBody TeacherDto teacher) {
        teacherService.changeTeacher(teacher);
    }

    @PutMapping(value = "students", produces = MediaType.APPLICATION_JSON_VALUE)
    public void changeStudent(@RequestBody StudentDto student) {
        teacherService.changeStudent(student);
    } //principal

    @DeleteMapping(value = "students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteStudent(@PathVariable Long id) {
        teacherService.deleteStudent(id);
    }

}