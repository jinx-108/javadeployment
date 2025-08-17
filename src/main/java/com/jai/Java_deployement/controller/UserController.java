package com.jai.Java_deployement.controller;


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
    public ResponseEntity<User> findUserById(@PathVariable int id){
        return new ResponseEntity<User>(userService.findUserById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody User user){
        return new ResponseEntity<User>(userService.addUser(user), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/test-error", produces = "application/json")
    public ResponseEntity<Object> testError() {
        Map<String, Object> error = new HashMap<>();
        error.put("status", 12);
        error.put("message", "does not exist in DB");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}
