package com.streakify.streakify_backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
@Getter
@Setter
public class HabitLogRequestDTO {

    @NotNull(message = "Date is required")
    private LocalDate logDate;
    private boolean completed;
}
