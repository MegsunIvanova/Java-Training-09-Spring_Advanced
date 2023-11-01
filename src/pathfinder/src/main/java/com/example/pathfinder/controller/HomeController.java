package com.example.pathfinder.controller;

import com.example.pathfinder.model.Route;
import com.example.pathfinder.service.RouteService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final RouteService routeService;

    public HomeController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/")
    @Transactional
    public String home(Model model) {
        Route route = this.routeService.getMostCommented();

        model.addAttribute("mostCommented", route);

        return "index";
    }
}
