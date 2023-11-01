package com.example.pathfinder.model.dto.binding;

import com.example.pathfinder.validation.FieldMatch;
import com.example.pathfinder.validation.UniqueEmail;
import com.example.pathfinder.validation.UniqueUsername;
import jakarta.validation.constraints.*;

@FieldMatch(first = "password", second = "confirmPassword", message = "Passwords do not match!")
public final class UserRegistrationDTO {
    @NotEmpty(message = "Username can not be empty!")
    @UniqueUsername
    @Size(min = 5, max = 20, message = "Username length must be between 2 and 20 symbols!")
    private String username; //Accepts values, which should be at least 2 characters

    @NotEmpty(message = "Full Name can not be empty!")
    @Size(min = 5, max = 20, message = "Full Name length must be between 2 and 20 symbols!")
    private String fullName;

    @NotEmpty(message = "Email can not be empty!")
    @Email(regexp = ".+[@].+", message = "Email must be valid!")
    @UniqueEmail
    private String email;

    @PositiveOrZero(message = "Age must be greater than equal to 0!")
    private int age;

    @NotEmpty
    @Size(min = 5, max = 20)
    private String password;

    @NotEmpty
    @Size(min = 5, max = 20)
    private String confirmPassword;

    public UserRegistrationDTO() {
    }

    public String getUsername() {
        return username;
    }

    public UserRegistrationDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserRegistrationDTO setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserRegistrationDTO setAge(int age) {
        this.age = age;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
