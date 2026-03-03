package com.streakify.streakify_backend.service;

import com.streakify.streakify_backend.dto.StreakSummaryDTO;
import com.streakify.streakify_backend.dto.UserDashboardResponseDTO;
import com.streakify.streakify_backend.entity.Habit;
import com.streakify.streakify_backend.exception.ResourceNotFoundException;
import com.streakify.streakify_backend.repository.HabitLogRepository;
import com.streakify.streakify_backend.repository.HabitRepository;
import com.streakify.streakify_backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DashboardService {
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;
    private final HabitLogRepository logRepository;
    private final StreakService streakService;

    public DashboardService(
            HabitRepository habitRepository,
            HabitLogRepository logRepository,
            StreakService streakService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.habitRepository = habitRepository;
        this.logRepository = logRepository;
        this.streakService = streakService;
    }

    public UserDashboardResponseDTO getDashboard(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        // 1️⃣ Total habits
        long totalHabits =
                habitRepository.countByUserId(userId);

        // 3️⃣ Completed today
        long completedToday =
                logRepository.countByHabit_UserIdAndLogDateAndCompletedTrue(
                        userId,
                        LocalDate.now());

        // 4️⃣ Streak details
        List<Habit> habits =
                habitRepository.findByUserId(userId);

        long activeHabits = habits.stream()
                .filter(habit ->
                        logRepository.existsByHabit_IdAndLogDateAfter(
                                habit.getId(),
                                LocalDate.now().minusDays(7)
                        )
                )
                .count();

        List<StreakSummaryDTO> streaks =
                habits.stream()
                        .map(habit -> {
                            var streak = streakService.getStreak(habit.getId());
                            return new StreakSummaryDTO(
                                    habit.getName(),
                                    streak.getCurrentStreak(),
                                    streak.getLongestStreak()
                            );
                        })
                        .toList();

        // 5️⃣ Consistency score
        int consistencyScore = calculateConsistency(userId);

        return new UserDashboardResponseDTO(
                totalHabits,
                activeHabits,
                completedToday,
                streaks,
                consistencyScore
        );
    }

    private int calculateConsistency(Long userId) {

        List<Habit> habits = habitRepository.findByUserId(userId);
        if (habits.isEmpty()) return 0;

        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);

        long daysPassed =
                ChronoUnit.DAYS.between(startOfWeek, today) + 1;

        long completedThisWeek = logRepository
                .findByHabit_UserId(userId)
                .stream()
                .filter(log -> log.isCompleted() &&
                        !log.getLogDate().isBefore(startOfWeek) &&
                        !log.getLogDate().isAfter(today))
                .count();

        double expectedTillToday = habits.stream()
                .mapToInt(Habit::getTargetDaysPerWeek)
                .sum() * (daysPassed / 7.0);

        if (expectedTillToday == 0) return 0;

        int score = (int)((completedThisWeek * 100) / expectedTillToday);

        return Math.min(score, 100);
    }
}