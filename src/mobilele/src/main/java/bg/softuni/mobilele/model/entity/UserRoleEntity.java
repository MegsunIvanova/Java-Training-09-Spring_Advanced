package bg.softuni.mobilele.model.entity;

import bg.softuni.mobilele.model.enums.UserRoleEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }

    @Override
    public String toString() {
        return "UserRoleEntity{" +
                "id=" + getId() +
                ", role=" + role.name() +
                '}';
    }
}
