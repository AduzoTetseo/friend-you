package com.example.FriendYou.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FriendYou.Models.LikePostPayload;
import com.example.FriendYou.Models.Post;
import com.example.FriendYou.Services.UserServices;

@RestController
@RequestMapping("/user")
public class PostController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/newPost")
    private Post addPost(@RequestBody Post post) throws Exception{
        try{
            if(post.getPost() == null || post.getPost().isEmpty()){
                throw new Exception("Write something to post");
            }
            if(post.getUserId() < 0){
                throw new Exception("User Id required to post!");
            }

            Post addPost = userServices.addPost(post);
            return addPost;
        }catch (Exception e){
            throw new WebServerException(e.getLocalizedMessage(), e);
        }
    }
    @PostMapping("/updatePost")
    private Post updatePost(@RequestBody Post user) throws Exception{
        try{
            // if(user.getEmail() == null || user.getEmail().isEmpty()){
            //     throw new Exception("Email required to Update record");
            // }
            Post updatePost =userServices.updatePost(user); 
            return updatePost;
        }catch(Exception e){
            throw new WebServerException(e.getLocalizedMessage(),e);
        }
    }

    @GetMapping("/getPost")
    private List<Post> getPost(Post post) throws Exception{
        try{
            return userServices.getPosts(post);
            
        }catch(Exception e){
            throw new WebServerException(e.getLocalizedMessage(), e);
        }
            
    }

    @PostMapping("/likeDislikePost")
    private Post likeDislikePost(@RequestBody LikePostPayload payload){
        try{
            return userServices.likeDislikePost(payload);
        }catch (Exception e){
            throw new WebServerException(e.getLocalizedMessage(), e);
        }
    }
}
