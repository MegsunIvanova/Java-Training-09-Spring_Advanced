package com.example.pathfinder.model.dto.binding;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CreateCommentDTO {
    private Long routeId;
    @NotEmpty(message = "Message should not be empty!")
    @Size(min = 10, message = "Message should be at least 10 characters!")
    private String textContent;

    public CreateCommentDTO() {
    }

    public Long getRouteId() {
        return routeId;
    }

    public CreateCommentDTO setRouteId(Long routeId) {
        this.routeId = routeId;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

    public CreateCommentDTO setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }
}
