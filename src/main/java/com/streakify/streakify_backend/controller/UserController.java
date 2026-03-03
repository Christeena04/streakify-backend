package com.streakify.streakify_backend.controller;

import com.streakify.streakify_backend.dto.UserRequestDTO;
import com.streakify.streakify_backend.dto.UserResponseDTO;
import com.streakify.streakify_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping
    public UserResponseDTO createUser(@Valid @RequestBody UserRequestDTO dto) {
        return service.createUser(dto);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable Long id){
        return service.getUser(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        service.deleteUser(id);
        return "User has been deleted";
    }
}
