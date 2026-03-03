package com.streakify.streakify_backend.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter


public class UserRequestDTO {


    @NotBlank(message = "Name required")
    private String name;
    @NotBlank(message = "Email required")
    @Email(message = "Invalid email")
    private String email;

}
