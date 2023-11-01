package com.example.pathfinder.model.dto.view;

import com.example.pathfinder.model.enums.Level;

public class UserProfileViewDTO {

    private String username;
    private String fullName;
    private Level level;
    private int age;

    public UserProfileViewDTO() {
    }

    public String getUsername() {
        return username;
    }

    public UserProfileViewDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserProfileViewDTO setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public UserProfileViewDTO setLevel(Level level) {
        this.level = level;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserProfileViewDTO setAge(int age) {
        this.age = age;
        return this;
    }
}
