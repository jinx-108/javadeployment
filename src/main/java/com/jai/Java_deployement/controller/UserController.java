package com.jai.Java_deployement.controller;


import com.jai.Java_deployement.dto.UserDto;
import com.jai.Java_deployement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jai.Java_deployement.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable int id){
        return new ResponseEntity<UserDto>(userService.findUserById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
        return new ResponseEntity<UserDto>(userService.addUser(userDto), HttpStatus.CREATED);
    }

    @PostMapping(value = "/withoutdto", produces = "application/json")
    public ResponseEntity<User> addUserWithoutDto(@RequestBody User user){
        return new ResponseEntity<User>(userService.addUserWithoutDto(user), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<List<UserDto>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/test-error", produces = "application/json")
    public ResponseEntity<Object> testError() {
        Map<String, Object> error = new HashMap<>();
        error.put("status", 12);
        error.put("message", "does not exist in DB");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}
