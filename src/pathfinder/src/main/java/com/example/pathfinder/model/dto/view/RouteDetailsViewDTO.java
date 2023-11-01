package com.example.pathfinder.model.dto.view;

import com.example.pathfinder.model.Picture;
import com.example.pathfinder.model.User;
import com.example.pathfinder.model.enums.Level;

import java.util.List;
import java.util.Set;

public class RouteDetailsViewDTO {
    private Long id;
    private String name;
    private String authorName;
    private Level level;
    private String description;
    private String videoUrl;
    private List<CommentViewDTO> comments;

//    private Set<Picture> pictures;

    //TODO: pass coordinates;

    public RouteDetailsViewDTO() {
    }

    public Long getId() {
        return id;
    }

    public RouteDetailsViewDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteDetailsViewDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public RouteDetailsViewDTO setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteDetailsViewDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public RouteDetailsViewDTO setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public RouteDetailsViewDTO setLevel(Level level) {
        this.level = level;
        return this;
    }

    public List<CommentViewDTO> getComments() {
        return comments;
    }

    public RouteDetailsViewDTO setComments(List<CommentViewDTO> comments) {
        this.comments = comments;
        return this;
    }
}
