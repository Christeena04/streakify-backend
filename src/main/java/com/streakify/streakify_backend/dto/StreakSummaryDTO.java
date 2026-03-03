package com.streakify.streakify_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StreakSummaryDTO {

    private String habitName;
    private int currentStreak;
    private int longestStreak;
}
