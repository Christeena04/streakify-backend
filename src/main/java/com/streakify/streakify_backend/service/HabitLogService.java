package com.streakify.streakify_backend.service;

import com.streakify.streakify_backend.dto.HabitLogRequestDTO;
import com.streakify.streakify_backend.dto.HabitLogResponseDTO;
import com.streakify.streakify_backend.dto.HabitProgressDTO;
import com.streakify.streakify_backend.entity.Habit;
import com.streakify.streakify_backend.entity.HabitLog;
import com.streakify.streakify_backend.exception.ResourceNotFoundException;
import com.streakify.streakify_backend.repository.HabitLogRepository;
import com.streakify.streakify_backend.repository.HabitRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
public class HabitLogService {

    private final HabitLogRepository logRepository;
    private final HabitRepository habitRepository;

    public HabitLogService(HabitLogRepository logRepository,
                           HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
        this.logRepository = logRepository;
    }

    public HabitProgressDTO logHabit(Long habitId, HabitLogRequestDTO dto) {

        if (dto.getLogDate().isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Future date not allowed");

        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Habit not found"));

        if (logRepository.existsByHabit_IdAndLogDate(habitId, dto.getLogDate()))
            throw new IllegalArgumentException("Already logged");

        HabitLog log = new HabitLog();
        log.setLogDate(dto.getLogDate());
        log.setCompleted(dto.isCompleted());
        log.setHabit(habit);

        logRepository.save(log);

        LocalDate startOfWeek = dto.getLogDate().with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = startOfWeek.plusDays(6);

        long completedDays =
                logRepository.countByHabit_IdAndCompletedTrueAndLogDateBetween(
                        habitId, startOfWeek, endOfWeek);

        int targetDays = habit.getTargetDaysPerWeek();

        String message =
                completedDays > targetDays ? "Target exceeded! 🎉"
                        : completedDays == targetDays ? "Target achieved! 🎯"
                        : "Keep going!";

        return new HabitProgressDTO(completedDays, targetDays, message);
    }


    public HabitLogResponseDTO updateLog(Long habitId,
                                         String date,
                                         HabitLogRequestDTO dto) {

        LocalDate logDate = LocalDate.parse(date);

        if (logDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Future date not allowed");

        HabitLog log = logRepository
                .findByHabit_IdAndLogDate(habitId, logDate)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Log not found"));

        log.setCompleted(dto.isCompleted());

        return convertToDTO(logRepository.save(log));
    }


    public List<HabitLogResponseDTO> getAllLogs(Long habitId) {
        habitRepository.findById(habitId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Habit not found"));


        List<HabitLog> logs =
                logRepository.findByHabit_Id(habitId);

        return logs.stream()
                .map(this::convertToDTO)
                .toList();
    }


    private HabitLogResponseDTO convertToDTO(HabitLog log) {
        return new HabitLogResponseDTO(
                log.getId(),
                log.getLogDate(),
                log.isCompleted(),
                log.getHabit().getId()
        );
    }
}
