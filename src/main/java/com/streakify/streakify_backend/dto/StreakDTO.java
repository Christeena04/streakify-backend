package com.streakify.streakify_backend.dto;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class StreakDTO {

    private int currentStreak;
    private int longestStreak;

    public StreakDTO(int currentStreak, int longestStreak) {
        this.currentStreak = currentStreak;
        this.longestStreak = longestStreak;
    }
}

