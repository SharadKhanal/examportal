package com.examportalservice.helper;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(){
        super("User with this Username is  not found in DB, please use any other name!");
    }
    public  UserNotFoundException(String msg){
        super(msg);
    }
}
