package com.example.crud.repository;

import com.example.crud.dto.UserDto;
import com.example.crud.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByIsDeletedFalse();

    Optional<User> findByIdAndIsDeletedFalse(Long id);

}
