package com.examportalservice.helper;

import com.examportalservice.entity.User;

public class UserFoundException extends Exception{

    public UserFoundException(){
        super("User with this username is already found in DB, please try with any new one!!");
    }

    public UserFoundException(String msg){
        super(msg);
    }
}
