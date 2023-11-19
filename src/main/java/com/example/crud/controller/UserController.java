package com.example.crud.controller;

import com.example.crud.dto.UserDto;
import com.example.crud.entities.User;
import com.example.crud.exception.ResourceNotFoundException;
import com.example.crud.serrvices.impl.UserImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private UserImpl userImpl;
    public UserController(UserImpl userImpl) {
        this.userImpl = userImpl;
    }
    @PostMapping
    public ResponseEntity<User> save(@RequestBody() User user){
        User saveUser = this.userImpl.addUser(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() throws ResourceNotFoundException {
        List<User> users  =  this.userImpl.getUsers();
        return  ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUsersById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        User user = (User) this.userImpl.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@RequestBody() User user, @PathVariable("id") Long id) throws ResourceNotFoundException {
        User users = this.userImpl.updateUser(user, id);
        return  ResponseEntity.ok(users);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
            this.userImpl.deleteUser(id);
           return ResponseEntity.ok("User Deleted Successfully");
    }

}
