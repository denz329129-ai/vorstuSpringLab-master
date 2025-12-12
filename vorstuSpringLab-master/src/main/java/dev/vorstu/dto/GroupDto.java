package dev.vorstu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class GroupDto {
    private Long id;
    private String groupName;
}
