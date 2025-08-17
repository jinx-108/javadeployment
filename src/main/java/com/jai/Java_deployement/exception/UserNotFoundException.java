package com.jai.Java_deployement.exception;

public class UserNotFoundException extends RuntimeException{

    int userId;
    public UserNotFoundException(int userId, String message ){
        super(String.format("User with userid %s %s", userId, message));
        this.userId = userId;
    }
}
