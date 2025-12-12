package dev.vorstu.dto;

import dev.vorstu.entity.Group;
import dev.vorstu.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private Role role;
    private boolean enable;


}
