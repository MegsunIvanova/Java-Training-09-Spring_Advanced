package com.example.pathfinder.model;

import com.example.pathfinder.model.enums.UserRole;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private UserRole name;

    public Role() {
    }

    public long getId() {
        return id;
    }

    public Role setId(long id) {
        this.id = id;
        return this;
    }

    public UserRole getName() {
        return name;
    }

    public Role setName(UserRole name) {
        this.name = name;
        return this;
    }
}
