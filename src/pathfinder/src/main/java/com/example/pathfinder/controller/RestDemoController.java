package com.example.pathfinder.controller;

import com.example.pathfinder.model.User;
import com.example.pathfinder.service.RestDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class RestDemoController {
    private final RestDemoService userService;

    @Autowired
    public RestDemoController(RestDemoService userService) {
        this.userService = userService;
    }

//    @RequestMapping(path ="/users/all", method = RequestMethod.GET)
//    or:
    @GetMapping("/users/all")
//    @ResponseBody
    public List<User> getALl () {
       return this.userService.getAll();
    }
}
