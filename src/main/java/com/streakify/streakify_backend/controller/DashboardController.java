package com.streakify.streakify_backend.controller;

import com.streakify.streakify_backend.dto.UserDashboardResponseDTO;
import com.streakify.streakify_backend.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/{userId}/dashboard")
    public UserDashboardResponseDTO getDashboard(
            @PathVariable Long userId) {

        return service.getDashboard(userId);
    }
}
