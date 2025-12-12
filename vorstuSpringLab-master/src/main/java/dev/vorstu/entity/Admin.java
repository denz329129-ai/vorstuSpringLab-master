package dev.vorstu.entity;

import dev.vorstu.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "admins")
@Getter
@Setter
@NoArgsConstructor
public class Admin extends User {
    public Admin(Long id, String username, Role role, boolean enable, Password password) {
        super(id, username, role, enable, password);

    }
}

