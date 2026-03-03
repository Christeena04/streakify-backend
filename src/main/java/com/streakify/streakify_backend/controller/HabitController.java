package com.streakify.streakify_backend.controller;
import com.streakify.streakify_backend.dto.HabitRequestDTO;
import com.streakify.streakify_backend.dto.HabitResponseDTO;
import com.streakify.streakify_backend.service.HabitService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HabitController {

    private final HabitService service;

    public HabitController(HabitService service) {
        this.service = service;
    }

    @PostMapping("/habits")
    public HabitResponseDTO createHabit(@Valid @RequestBody HabitRequestDTO dto) {
        return service.createHabit(dto);
    }

    @GetMapping("/users/{userId}/habits")
    public List<HabitResponseDTO> getHabitsByUser(@PathVariable Long userId) {
        return service.getHabitsByUser(userId);
    }

    @DeleteMapping("/habits/{id}")
    public String deleteHabit(@PathVariable Long id) {
        service.deleteHabit(id);
        return "Habit deleted successfully";
    }
}
