package com.streakify.streakify_backend.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HabitLogResponseDTO {

    private Long id;
    private LocalDate logDate;
    private boolean completed;
    private Long habitId;

}
