package dev.vorstu.dto;

import dev.vorstu.entity.Group;
import dev.vorstu.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeacherDto {
    private Long id;
    private String username;
    private Role role;
    private String fio;
    private String phoneNumber;
    private List<Group> groups;
    private boolean enable;
}
