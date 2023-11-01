package com.example.pathfinder.service.impl;

import com.example.pathfinder.exeptions.UserNotFoundException;
import com.example.pathfinder.model.User;
import com.example.pathfinder.model.dto.binding.UserLoginDTO;
import com.example.pathfinder.model.dto.binding.UserRegistrationDTO;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.service.AuthService;
import com.example.pathfinder.service.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final LoggedUser loggedUser;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    @Override
    public void register(UserRegistrationDTO registrationDTO) {
        User user = modelMapper.map(registrationDTO, User.class);
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        this.userRepository.save(user);
    }

    @Override
    public boolean login(UserLoginDTO loginDTO) {
        String username = loginDTO.getUsername();

        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with that username: " +
                        username + " is not present"));

        boolean passwordMatch = passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());

        if (!passwordMatch) {
            throw new UserNotFoundException("User entered incorrect password");
        }

        loggedUser.setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setFullName(user.getFullName());

//        System.out.println(loggedUser.getUsername());

        return true;
    }

    @Override
    public void logout() {
        loggedUser.reset();
    }
}
