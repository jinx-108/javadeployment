package com.jai.Java_deployement.service;

import com.jai.Java_deployement.entity.User;
import com.jai.Java_deployement.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import com.jai.Java_deployement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    public User findUserById(int id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id, " does not exist in the DB"));
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
