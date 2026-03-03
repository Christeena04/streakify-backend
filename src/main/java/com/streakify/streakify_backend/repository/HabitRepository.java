package com.streakify.streakify_backend.repository;

import com.streakify.streakify_backend.entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit,Long> {
    List<Habit> findByUserId(Long userid);
    long countByUserId(Long userId);
    boolean existsByUserIdAndName(Long userId, String name);
}
