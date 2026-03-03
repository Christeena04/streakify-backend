package com.streakify.streakify_backend.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table (name = "habitLogs",uniqueConstraints = {@UniqueConstraint(columnNames = {"habitId","logDate"})})
public class HabitLog {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate logDate;
    private boolean completed;
    @ManyToOne
    @JoinColumn(name = "habitId")
    @JsonIgnore
    private Habit habit;
    public HabitLog(){}
}

