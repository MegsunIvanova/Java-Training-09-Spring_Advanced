package bg.softuni.mobilele.model.entity;

import bg.softuni.mobilele.model.enums.UserRoleEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

//    public Long getId() {
//        return id;
//    }
//
//    public UserRoleEntity setId(Long id) {
//        this.id = id;
//        return this;
//    }

    public String getName() {
        return name;
    }

    public UserRoleEntity setName(String name) {
        this.name = name;
        return this;
    }

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
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
}
