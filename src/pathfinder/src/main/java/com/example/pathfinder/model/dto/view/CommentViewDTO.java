package com.example.pathfinder.model.dto.view;

public class CommentViewDTO {
    private String textContent;
    private String authorName;

    public CommentViewDTO() {
    }

    public String getTextContent() {
        return textContent;
    }

    public CommentViewDTO setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public CommentViewDTO setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }
}
