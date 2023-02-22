package com.example.FriendYou.Controller;

import com.example.FriendYou.Models.User;
import com.example.FriendYou.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/user")
public class SingUpController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/signup")
    private User addUser(@RequestBody User user)throws Exception{
        try{
        if(user.getUsername() == null || user.getUsername().isEmpty()){
            throw new Exception("User Name is Required");
        }
        if(user.getEmail() == null || user.getEmail().isEmpty()){
            throw new Exception("Email is Required");
        }
        if(user.getPassword() == null || user.getPassword().isEmpty()){
            throw new Exception("Password is Required");
        }
        return userServices.addUser(user);
    }catch(Exception e){
            throw new WebServerException(e.getLocalizedMessage(), e);
        }

        }
       
}
