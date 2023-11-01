package com.example.pathfinder.service;

import com.example.pathfinder.model.dto.binding.UserLoginDTO;
import com.example.pathfinder.model.dto.binding.UserRegistrationDTO;

public interface AuthService {
    void register(UserRegistrationDTO registrationDTO);

    boolean login(UserLoginDTO loginDTO);

    void logout();
}
