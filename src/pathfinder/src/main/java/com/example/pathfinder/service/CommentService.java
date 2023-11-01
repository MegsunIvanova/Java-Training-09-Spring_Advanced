package com.example.pathfinder.service;

import com.example.pathfinder.model.dto.binding.CreateCommentDTO;

public interface CommentService {
    void create(CreateCommentDTO createCommentDTO);
}
