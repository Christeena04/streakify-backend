package com.streakify.streakify_backend.repository;
import com.streakify.streakify_backend.entity.HabitLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
public interface HabitLogRepository extends JpaRepository<HabitLog, Long> {
    // Get all logs for a habit
    List<HabitLog> findByHabit_Id(Long habitId);
    // Prevent duplicate log for same day
    boolean existsByHabit_IdAndLogDate(Long habitId, LocalDate logDate);
    // Count completed logs in date range (weekly/monthly progress)
    long countByHabit_IdAndCompletedTrueAndLogDateBetween(
            Long habitId,
            LocalDate start,
            LocalDate end
    );
    // Fetch specific log (needed for update API)
    Optional<HabitLog> findByHabit_IdAndLogDate(
            Long habitId,
            LocalDate logDate
    );
    // ⭐ Best method for streak logic (only completed logs sorted)
    List<HabitLog> findByHabit_IdAndCompletedTrueOrderByLogDateAsc(Long habitId);
    long countByHabit_UserIdAndLogDateAndCompletedTrue(
            Long userId,
            LocalDate date
    );
    List<HabitLog> findByHabit_UserId(Long userId);
    boolean existsByHabit_IdAndLogDateAfter(
            Long habitId,
            LocalDate date
    );
}