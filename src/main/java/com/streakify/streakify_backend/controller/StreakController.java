package com.streakify.streakify_backend.controller;
import com.streakify.streakify_backend.dto.StreakDTO;
import com.streakify.streakify_backend.service.StreakService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/habits")
public class StreakController {

    private final StreakService service;

    public StreakController(StreakService service){
        this.service = service;
    }

    @GetMapping("/{habitId}/streak")
    public StreakDTO getStreak(@PathVariable Long habitId) {
        return service.getStreak(habitId);
    }
}
