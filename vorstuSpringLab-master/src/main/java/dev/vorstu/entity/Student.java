package dev.vorstu.entity;

import dev.vorstu.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User{

    private String fio;

    @Column(name = "phone_number")
    private String phoneNumber;

    //todo сохранять только по id (joinColumn)
    private Long groupId;

    //todo lazy ,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private Group group;

    public Student(Long id, String username, Role role, boolean enable,
                   Password password, String fio, String phoneNumber, Group group) {
        super(id, username, role, enable, password);
        this.fio = fio;
        this.phoneNumber = phoneNumber;
        this.group = group;
    }

}
