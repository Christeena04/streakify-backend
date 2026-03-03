package com.streakify.streakify_backend.service;
import com.streakify.streakify_backend.dto.HabitRequestDTO;
import com.streakify.streakify_backend.dto.HabitResponseDTO;
import com.streakify.streakify_backend.entity.Habit;
import com.streakify.streakify_backend.entity.User;
import com.streakify.streakify_backend.exception.ResourceNotFoundException;
import com.streakify.streakify_backend.repository.HabitRepository;
import com.streakify.streakify_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitService {

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public HabitService(HabitRepository habitRepository,
                        UserRepository userRepository) {
        this.habitRepository = habitRepository;
        this.userRepository = userRepository;
    }

    public HabitResponseDTO createHabit(HabitRequestDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
        // ⭐ Duplicate habit check
        boolean exists =
                habitRepository.existsByUserIdAndName(
                        dto.getUserId(),
                        dto.getName()
                );

        if (exists) {
            throw new IllegalArgumentException(
                    "Habit already exists for this user"
            );
        }

        Habit habit = new Habit();
        habit.setName(dto.getName());
        habit.setTargetDaysPerWeek(dto.getTargetDaysPerWeek());
        habit.setUser(user);

        Habit savedHabit = habitRepository.save(habit);

        return convertToDTO(savedHabit);
    }

    public List<HabitResponseDTO> getHabitsByUser(Long userId) {

           userRepository.findById(userId).orElseThrow(()->
                   new ResourceNotFoundException("User Not found"));
            List<Habit> habits =
                habitRepository.findByUserId(userId);

        return habits.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public void deleteHabit(Long habitId){
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Habit not found"));

        habitRepository.delete(habit);
    }

    private HabitResponseDTO convertToDTO(Habit habit) {
        return new HabitResponseDTO(
                habit.getId(),
                habit.getName(),
                habit.getTargetDaysPerWeek()
        );
    }
}
