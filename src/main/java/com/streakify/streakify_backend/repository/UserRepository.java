package com.streakify.streakify_backend.repository;

import com.streakify.streakify_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

}
