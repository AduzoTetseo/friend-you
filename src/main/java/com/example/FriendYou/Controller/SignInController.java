package com.example.FriendYou.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FriendYou.Models.User;
import com.example.FriendYou.Models.UserValidation;
import com.example.FriendYou.Services.UserServices;
import com.example.utils.ValidationUtilities;

@RestController
@RequestMapping("/user")
public class SignInController{

    @Autowired
    UserServices userServices;

    @PostMapping("/signIn")
    public User signInDetails(@RequestBody UserValidation validation)throws Exception{

        try{
            String email = validation.getEmail();
            String password = validation.getPassword();
            //Validate user name and password
    
            if(!ValidationUtilities.isValidEmail(email) || !ValidationUtilities.isValidPassword(password)){
                throw new Exception("Credentials not valid!");
            }
    
            //Verify username and password
            User user = userServices.validateSignIn(email, password);
            user.setPassword("***********");
            return user;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
   
}
