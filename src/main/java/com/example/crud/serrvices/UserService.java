package com.example.crud.serrvices;

import com.example.crud.dto.UserDto;
import com.example.crud.entities.User;
import com.example.crud.exception.ResourceNotFoundException;

import java.util.List;

public interface UserService {
    User addUser(User user);
     List<User> getUsers() throws ResourceNotFoundException;
    User getUserById(long id) throws ResourceNotFoundException;
    boolean deleteUser(long id);
    User updateUser(User users,  Long id) throws ResourceNotFoundException;
}
