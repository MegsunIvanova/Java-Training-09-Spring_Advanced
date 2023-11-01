package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.User;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.service.RestDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestDemoServiceImpl implements RestDemoService {

    private final UserRepository userRepository;

    @Autowired
    public RestDemoServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
