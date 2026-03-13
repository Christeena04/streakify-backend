package com.streakify.streakify_backend.service;

import com.streakify.streakify_backend.dto.StreakDTO;
import com.streakify.streakify_backend.entity.HabitLog;
import com.streakify.streakify_backend.exception.ResourceNotFoundException;
import com.streakify.streakify_backend.repository.HabitLogRepository;
import com.streakify.streakify_backend.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StreakService {

    private final HabitLogRepository logRepository;
    private final HabitRepository habitRepository;

    public StreakService(HabitLogRepository logRepository,
                         HabitRepository habitRepository) {
        this.logRepository = logRepository;
        this.habitRepository = habitRepository;
    }

    // Main method (Controller calls this)
    public StreakDTO getStreak(Long habitId) {

        // Validate habit once
        habitRepository.findById(habitId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Habit not found"));

        // Fetch logs once
        List<HabitLog> logs =
                logRepository.findByHabit_IdAndCompletedTrueOrderByLogDateAsc(habitId);

        int current = calculateCurrentStreakFromLogs(logs);
        int longest = calculateLongestStreakFromLogs(logs);

        return new StreakDTO(current, longest);
    }

    // ⭐ Current streak calculation
    private int calculateCurrentStreakFromLogs(List<HabitLog> logs) {

        if (logs.isEmpty()) return 0;

        int streak = 0;
        LocalDate today = LocalDate.now();

        // Start from today if completed,
        // otherwise start from yesterday
        LocalDate checkDate = today;

        // Check if today is NOT logged
        HabitLog lastLog = logs.get(logs.size() - 1);

        if (!lastLog.getLogDate().equals(today)) {
            checkDate = today.minusDays(1);
        }

        for (int i = logs.size() - 1; i >= 0; i--) {
            HabitLog log = logs.get(i);

            if (log.getLogDate().equals(checkDate)) {
                streak++;
                checkDate = checkDate.minusDays(1);
            } else if (log.getLogDate().isBefore(checkDate)) {
                break;
            }
        }

        return streak;
    }
    // Longest streak calculation
    private int calculateLongestStreakFromLogs(List<HabitLog> logs) {

        int longest = 0;
        int current = 0;
        LocalDate previousDate = null;

        for (HabitLog log : logs) {

            if (previousDate == null) {
                current = 1;
            } else if (log.getLogDate().equals(previousDate.plusDays(1))) {
                current++;
            } else {
                current = 1;
            }

            longest = Math.max(longest, current);
            previousDate = log.getLogDate();
        }

        return longest;
    }
}