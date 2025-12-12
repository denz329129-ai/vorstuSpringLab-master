package dev.vorstu.controllers;

import dev.vorstu.dto.StudentDto;
import dev.vorstu.jwt.JwtAuthentication;
import dev.vorstu.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/student")

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/{id}/{page}/{size}")
    public Page<StudentDto> getStudentsWithPagination(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size") int size,

            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(name = "sort", defaultValue = "id,asc") String sort){

        //todo pageutils??
            String[] parts = sort.split(",");
            String field = parts[0];
            Sort.Direction direction = Sort.Direction.fromString(parts[1].toUpperCase());
            if (field.equals("group")) {
                field = "group.groupName";
            }

            PageRequest pageRequeest = PageRequest.of(page, size, Sort.by(direction, field));

        return studentService.getStudentsWithPagination(id, name, pageRequeest);
    }

    @GetMapping("me")
    public StudentDto getCurrentStudent(JwtAuthentication authentication) {
        Long id = authentication.getUserId();
        return studentService.getCurrentStudent(id);
    }

    @PutMapping(value = "me", produces = MediaType.APPLICATION_JSON_VALUE)
    public void changeStudent(@RequestBody StudentDto student, JwtAuthentication authentication, Principal principal) {

        //todo long vs long, Long vs Long, Long vs long
        if(student.getId() == authentication.getUserId()){
            studentService.changeStudent(student);
        }

    }



}
