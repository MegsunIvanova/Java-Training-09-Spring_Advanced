package com.example.pathfinder.service.impl;

import com.example.pathfinder.exeptions.UserNotFoundException;
import com.example.pathfinder.model.User;
import com.example.pathfinder.model.dto.view.UserProfileViewDTO;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.service.UserService;
import com.example.pathfinder.service.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final LoggedUser loggedUser;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(LoggedUser loggedUser, UserRepository userRepository, ModelMapper modelMapper) {
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserProfileViewDTO getUserProfile() {
        User user = getLoggedUser();
        return modelMapper.map(user, UserProfileViewDTO.class);
    }

    private User getLoggedUser() {
        String username = loggedUser.getUsername();

        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with username: " + username + " not found!")
                );
    }


}
