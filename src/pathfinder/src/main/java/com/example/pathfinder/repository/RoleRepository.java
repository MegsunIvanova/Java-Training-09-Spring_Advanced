package com.example.pathfinder.repository;

import com.example.pathfinder.model.Role;
import com.example.pathfinder.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName (UserRole role);
}
