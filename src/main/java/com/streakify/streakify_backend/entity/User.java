package com.streakify.streakify_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table (name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private  String name;
    @Column(unique = true, nullable = false)
    @NotBlank(message="Email required")
    @Email(message="Invalid email")
    private String email;

    private LocalDateTime createdAt;
    @PrePersist
    public void onCreate(){

        this.createdAt=LocalDateTime.now();
    }
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Habit> habits;
    public  User(){}  //default constructor
}



