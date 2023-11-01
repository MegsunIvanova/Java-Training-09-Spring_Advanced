package com.example.pathfinder.model.dto.binding;

import com.example.pathfinder.validation.FileAnnotation;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class UploadPictureRouteDTO {

    private Long id;
    @NotEmpty
    private String title;
    @FileAnnotation(contentTypes = {"image/png", "image/jpeg"})
    private MultipartFile picture;

    public UploadPictureRouteDTO() {
    }

    public Long getId() {
        return id;
    }

    public UploadPictureRouteDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public UploadPictureRouteDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public UploadPictureRouteDTO setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
