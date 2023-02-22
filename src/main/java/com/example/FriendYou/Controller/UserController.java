package com.example.FriendYou.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FriendYou.Models.User;
import com.example.FriendYou.Services.UserServices;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;
    
    @PostMapping("/updateuser")
    private User updateUser(@RequestBody User user) throws Exception{
            try{
               
                if(user.getEmail() == null || user.getEmail().isEmpty()){
                    throw new Exception("Email required to Update record");
                }
               
                User userUpdate = userServices.updateUser(user);
                return userUpdate;
        }catch(Exception e){
        throw new WebServerException(e.getLocalizedMessage(), e);
    }
}


 
}
