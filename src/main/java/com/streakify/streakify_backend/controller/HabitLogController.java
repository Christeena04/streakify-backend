package com.streakify.streakify_backend.controller;

import com.streakify.streakify_backend.dto.HabitLogRequestDTO;
import com.streakify.streakify_backend.dto.HabitLogResponseDTO;
import com.streakify.streakify_backend.dto.HabitProgressDTO;
import com.streakify.streakify_backend.service.HabitLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habits")
public class HabitLogController {

    private final HabitLogService service;

    public HabitLogController(HabitLogService service){
        this.service = service;
    }

    @PostMapping("/{habitId}/logs")
    public HabitProgressDTO logHabit(
            @PathVariable Long habitId,
            @RequestBody HabitLogRequestDTO dto) {

        return service.logHabit(habitId, dto);
    }

    @PutMapping("/{habitId}/logs/{date}")
    public HabitLogResponseDTO updateLog(
            @PathVariable Long habitId,
            @PathVariable String date,
            @RequestBody HabitLogRequestDTO dto) {

        return service.updateLog(habitId, date, dto);
    }

    @GetMapping("/{habitId}/logs")
    public List<HabitLogResponseDTO> getAllLogs(
            @PathVariable Long habitId) {

        return service.getAllLogs(habitId);
    }
}