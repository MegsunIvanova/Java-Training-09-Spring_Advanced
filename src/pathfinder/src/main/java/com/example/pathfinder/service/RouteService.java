package com.example.pathfinder.service;

import com.example.pathfinder.model.Route;
import com.example.pathfinder.model.dto.binding.AddRouteDTO;
import com.example.pathfinder.model.dto.binding.UploadPictureRouteDTO;
import com.example.pathfinder.model.dto.view.RouteCategoryViewDTO;
import com.example.pathfinder.model.dto.view.RouteDetailsViewDTO;
import com.example.pathfinder.model.dto.view.RouteViewDTO;
import com.example.pathfinder.model.enums.CategoryName;

import java.util.List;

public interface RouteService {
    Route getMostCommented();

    void add(AddRouteDTO routeDTO);

    List<RouteViewDTO> getAll();

    RouteDetailsViewDTO getDetails(Long id);

    void uploadPicture(UploadPictureRouteDTO uploadPictureRouteDTO);

    List<RouteCategoryViewDTO> getAllByCategory(CategoryName categoryName);
}
