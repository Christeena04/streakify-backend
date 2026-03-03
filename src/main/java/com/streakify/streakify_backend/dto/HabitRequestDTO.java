package com.streakify.streakify_backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class HabitRequestDTO {
    @NotNull(message = "UserId required")
    private Long userId;
    @NotBlank(message = "Habit Name required")
    private String name;

    @Min(value = 1, message = "Minimum 1 day required")
    @Max(value = 7,message = "Maximum 7 days only")
    private int targetDaysPerWeek;

}
