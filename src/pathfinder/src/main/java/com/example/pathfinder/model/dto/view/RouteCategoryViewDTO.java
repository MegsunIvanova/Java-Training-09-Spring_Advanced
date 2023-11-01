package com.example.pathfinder.model.dto.view;

public class RouteCategoryViewDTO {
    private Long id;
    private String name;
    private String description;

    public RouteCategoryViewDTO() {
    }

    public Long getId() {
        return id;
    }

    public RouteCategoryViewDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteCategoryViewDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteCategoryViewDTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
