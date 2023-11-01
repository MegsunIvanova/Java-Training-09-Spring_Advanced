package com.example.pathfinder.service.impl;

import com.example.pathfinder.exeptions.RouteNotFoundException;
import com.example.pathfinder.exeptions.UserNotFoundException;
import com.example.pathfinder.model.Comment;
import com.example.pathfinder.model.Route;
import com.example.pathfinder.model.User;
import com.example.pathfinder.model.dto.binding.CreateCommentDTO;
import com.example.pathfinder.repository.CommentRepository;
import com.example.pathfinder.repository.RouteRepository;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.service.CommentService;
import com.example.pathfinder.service.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {
    private final RouteRepository routeRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;

    public CommentServiceImpl(RouteRepository routeRepository, CommentRepository commentRepository, ModelMapper modelMapper, UserRepository userRepository, LoggedUser loggedUser) {
        this.routeRepository = routeRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    @Override
    public void create(CreateCommentDTO createCommentDTO) {
        Route route = routeRepository
                .findById(createCommentDTO.getRouteId())
                .orElseThrow(() -> new RouteNotFoundException("Route not found"));

        User author = userRepository.findByUsername(loggedUser.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        Comment comment = modelMapper.map(createCommentDTO, Comment.class);
        comment.setCreated(LocalDateTime.now())
                .setRoute(route)
                .setAuthor(author);

        commentRepository.save(comment);
    }
}
