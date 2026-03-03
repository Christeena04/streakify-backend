package com.streakify.streakify_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDashboardResponseDTO {

    private long totalHabits;
    private long activeHabits;
    private long completedToday;
    private List<StreakSummaryDTO> currentStreaks;
    private int consistencyScore;
}