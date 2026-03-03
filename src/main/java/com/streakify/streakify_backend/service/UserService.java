package com.streakify.streakify_backend.service;

import com.streakify.streakify_backend.dto.UserRequestDTO;
import com.streakify.streakify_backend.dto.UserResponseDTO;
import com.streakify.streakify_backend.entity.User;
import com.streakify.streakify_backend.exception.ResourceNotFoundException;
import com.streakify.streakify_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    // CREATE USER
    public UserResponseDTO createUser(UserRequestDTO dto) {

        User user = convertToEntity(dto);
        User savedUser = repository.save(user);

        return convertToDTO(savedUser);
    }

    // GET USER
    public UserResponseDTO getUser(Long id) {

        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));


        return convertToDTO(user);
    }

    // DELETE USER
    public String deleteUser(Long id) {

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        repository.delete(user);
        return "User Deleted Successfully......";
    }


    // ENTITY → DTO
    private UserResponseDTO convertToDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()


        );
    }

    // DTO → ENTITY
    private User convertToEntity(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }
}
