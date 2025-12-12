package dev.vorstu.controllers;

import dev.vorstu.dto.*;
import dev.vorstu.jwt.JwtAuthentication;
import dev.vorstu.repositories.AdminRepository;
import dev.vorstu.services.AdminService;
import dev.vorstu.services.RegisterService;
import dev.vorstu.services.TeacherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "admin", description = "админское")
@RequestMapping("api/admin")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AdminController {

    private final AdminService adminService;
    private final TeacherService teacherService;
    private final RegisterService registerService;

    @GetMapping("/{id}/{page}/{size}")
    public Page<StudentDto> getStudentsWithPagination(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size") int size,

            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(name = "sort", defaultValue = "id,asc") String sort){

        //todo копипаста
        String[] parts = sort.split(",");
        String field = parts[0];
        Sort.Direction direction = Sort.Direction.fromString(parts[1].toUpperCase());
            if (field.equals("group")) {
                field = "groups.groupName";
            }

            PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction, field));

        return adminService.getStudentsWithPagination(name, pageRequest);
    }

    @GetMapping("teachers/{page}/{size}")
    public Page<TeacherDto> getTeachersWithPagination(
            @PathVariable(name = "page") int page,
            @PathVariable(name = "size") int size,

            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(name = "sort", defaultValue = "id,asc") String sort)
    {
        String[] parts = sort.split(",");
        String field = parts[0];
        Sort.Direction direction = Sort.Direction.fromString(parts[1].toUpperCase());
        if (field.equals("group")) {
            field = "group.groupName";
        }

        PageRequest pageRequeest = PageRequest.of(page, size, Sort.by(direction, field));

        return teacherService.getTeachersWithPagination(name, pageRequeest);
    }

    @DeleteMapping(value = "teachers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteTeacher(@PathVariable Long id) {
        adminService.deleteTeacher(id);
    }

    @GetMapping("me")
    public AdminDto getCurrentUser(JwtAuthentication authentication) {
        Long id = authentication.getUserId();
        return adminService.getCurrentUser(id);
    }

    @PostMapping("teacher")
    public void createNewTeacher(@RequestBody RegisterRequest registerRequest) {
        registerService.registerTeacher(registerRequest);
    }

    @PutMapping(value = "me", produces = MediaType.APPLICATION_JSON_VALUE)
    public void changeAdmin(@RequestBody AdminDto admin) {
        adminService.changeAdmin(admin);
    }

    @PutMapping(value = "students", produces = MediaType.APPLICATION_JSON_VALUE)
    public void changeStudent(@RequestBody StudentDto student) {
        adminService.changeStudent(student);
    }

    @DeleteMapping(value = "students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteStudent(@PathVariable Long id) {
        adminService.deleteStudent(id);
    }

    @PostMapping(value = "group")
    public void createNewGroup(@RequestBody GroupDto group) {
        adminService.createNewGroup(group);
    }

    @PutMapping(value = "group")
    public void changeGroup(@RequestBody GroupDto group) {
        adminService.changeGroup(group);
    }

    @DeleteMapping(value = "group/{id}")
    public void deleteGroup(@PathVariable Long id) {
        adminService.deleteGroup(id);
    }
}
