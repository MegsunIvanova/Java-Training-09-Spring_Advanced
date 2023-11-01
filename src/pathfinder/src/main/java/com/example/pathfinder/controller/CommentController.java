package com.example.pathfinder.controller;

import com.example.pathfinder.model.dto.binding.CreateCommentDTO;
import com.example.pathfinder.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid CreateCommentDTO createCommentDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("createCommentDTO", createCommentDTO);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createCommentDTO", bindingResult);
        } else {
            commentService.create(createCommentDTO);
        }
        return new ModelAndView("redirect:/routes/details/" + createCommentDTO.getRouteId());
    }
}
