package com.example.pathfinder.model.dto.view;

public class RouteViewDTO {
    private Long id;
    private String imageUrl;
    private String name;
    private String description;

    public RouteViewDTO() {
    }

    public Long getId() {
        return id;
    }

    public RouteViewDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public RouteViewDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteViewDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteViewDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
