package com.example.utils;

public class ValidationUtilities{

    public static boolean isValidPassword(String password){
        if(password == null || password.isBlank())
            return false;

        //Validate with regular expression
        
        
        return true;
    }

    public static boolean isValidEmail(String email){
        if(email == null || email.isBlank())
            return false;

        //Validate with regular expression
        return true;
    }
}