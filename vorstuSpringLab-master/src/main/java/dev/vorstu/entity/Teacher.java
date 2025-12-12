package dev.vorstu.entity;

import dev.vorstu.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@NoArgsConstructor
public class Teacher extends User {

    private String fio;
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "teacher_groups",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<Group> groups;

    public Teacher(Long id, String username, Role role, boolean enable,
                   Password password, String fio, String phoneNumber,
                   List<Group> groups) {
        super(id, username, role, enable, password);
        this.fio = fio;
        this.phoneNumber = phoneNumber;
        this.groups = groups;
    }
}