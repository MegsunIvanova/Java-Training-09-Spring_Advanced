package com.example.pathfinder.model.enums;

public enum Level {
    BEGINNER(1),
    INTERMEDIATE(2),
    ADVANCED(3);

    public final int difficulty;

    Level(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
