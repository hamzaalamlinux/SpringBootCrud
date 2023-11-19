package com.example.crud.serrvices.impl;

import com.example.crud.dto.UserDto;
import com.example.crud.entities.User;
import com.example.crud.exception.ResourceNotFoundException;
import com.example.crud.repository.UserRepository;
import com.example.crud.serrvices.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserImpl  implements UserService {

    private  UserRepository userRepository;

    public UserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        try {
            return this.userRepository.save(user);
        }catch (Exception exception){
            throw new RuntimeException("Something Went Wrong");
        }
    }

    @Override
    public List<User> getUsers() throws ResourceNotFoundException {
        try {
            List<User> users = userRepository.findByIsDeletedFalse();
            if (users.isEmpty()) {
                throw new ResourceNotFoundException("Users Not Found");
            }
            return  users;
        }catch (Exception exception){
            throw  new RuntimeException("Something went wrong");
        }
    }

    @Override
    public User getUserById(long id) throws ResourceNotFoundException {
        try {
            Optional<User> userOptional = userRepository.findByIdAndIsDeletedFalse(id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                return user;
            } else {
                throw new ResourceNotFoundException("User not found with id: " + id);
            }

        }catch (Exception exception){
            throw new  RuntimeException("Something went wrong");
        }
    }

    @Override
    public boolean deleteUser(long id) {
        try {
            Optional<User> userOptional = this.userRepository.findByIdAndIsDeletedFalse(id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setDeleted(true);
                this.userRepository.save(user);
                return true;
            } else {
                throw new ResourceNotFoundException("User not found with id: " + id);
            }
        }catch (Exception exception){
            throw  new RuntimeException("Something went wrong");
        }
    }

    @Override
    public User updateUser(User users, Long id) throws ResourceNotFoundException {
        try {
            Optional<User> userOptional = this.userRepository.findByIdAndIsDeletedFalse(id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setName(users.getName());
                user.setEmail(users.getEmail());
                user.setPassword(users.getPassword());
                return this.userRepository.save(user);
            } else {
                throw new ResourceNotFoundException("User not found with id: " + id);
            }
        }catch (Exception exception){
            throw  new RuntimeException("Something went wrong");
        }

    }
}
