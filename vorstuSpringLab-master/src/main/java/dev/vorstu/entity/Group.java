package dev.vorstu.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupName;

    public Group(String group) {
        this.groupName = group;
    }

    public Group(Long id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }

    public Group() {
    }

    //todo getters, setters
    public Long getId() {
        return this.id;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
