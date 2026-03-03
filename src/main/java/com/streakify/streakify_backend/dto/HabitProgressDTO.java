package com.streakify.streakify_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HabitProgressDTO {

    private long completedDays;
    private int targetDays;
    private String message;
    public HabitProgressDTO() {}
    public HabitProgressDTO(long completedDays,
                            int targetDays,
                            String message) {
        this.completedDays = completedDays;
        this.targetDays = targetDays;
        this.message = message;
    }

}
