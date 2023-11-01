package com.example.pathfinder.controller;

import com.example.pathfinder.model.dto.binding.AddRouteDTO;
import com.example.pathfinder.model.dto.binding.UploadPictureRouteDTO;
import com.example.pathfinder.model.dto.view.RouteCategoryViewDTO;
import com.example.pathfinder.model.dto.view.RouteDetailsViewDTO;
import com.example.pathfinder.model.dto.view.RouteViewDTO;
import com.example.pathfinder.model.enums.CategoryName;
import com.example.pathfinder.model.enums.Level;
import com.example.pathfinder.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/routes")
public class RoutesController {

    private final RouteService routeService;

    public RoutesController(RouteService routeService) {
        this.routeService = routeService;
    }

    @ModelAttribute("routeDTO")
    public AddRouteDTO addRouteDTO() {
        return new AddRouteDTO();
    }

    @GetMapping("/add")
    public ModelAndView addRoute() {

        ModelAndView modelAndView = new ModelAndView("add-route");

        modelAndView.addObject("levels", Level.values());
        modelAndView.addObject("categories", CategoryName.values());

        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addRoute(@Valid AddRouteDTO routeDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        final ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("routeDTO", routeDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.routeDTO", bindingResult);

            modelAndView.setViewName("redirect:add");

        } else {
            routeService.add(routeDTO);
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }

    @GetMapping
    public ModelAndView getAll() {
        List<RouteViewDTO> routes = routeService.getAll();

        ModelAndView modelAndView = new ModelAndView("routes");
        modelAndView.addObject("routes", routes);

        return modelAndView;
    }

    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable("id") Long id) {
        RouteDetailsViewDTO route = routeService.getDetails(id);

        ModelAndView modelAndView = new ModelAndView("route-details");
        modelAndView.addObject("route", route);
        modelAndView.addObject("levels", Level.values());

        return modelAndView;
    }

    @PostMapping("/upload-picture")
    public ModelAndView uploadPicture(@Valid UploadPictureRouteDTO uploadPictureRouteDTO,
                                      BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes) {
        final ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("uploadPictureRouteDTO", uploadPictureRouteDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.uploadPictureRouteDTO", bindingResult);

            modelAndView.setViewName("redirect:details/{routId}");
        }

        routeService.uploadPicture(uploadPictureRouteDTO);

        modelAndView.setViewName("redirect:/routes");

        return modelAndView;
    }

    @GetMapping("/{categoryName}")
    public ModelAndView getAllByCategory(@PathVariable("categoryName") CategoryName categoryName) {
        List<RouteCategoryViewDTO> routes = routeService.getAllByCategory(categoryName);

        String view =
                switch (categoryName) {
                    case CAR -> "car";
                    case BICYCLE -> "bicycle";
                    case PEDESTRIAN -> "pedestrian";
                    case MOTORCYCLE -> "motorcycle";
                };

        ModelAndView modelAndView = new ModelAndView(view);
        modelAndView.addObject("routes", routes);

        return modelAndView;
    }
}
