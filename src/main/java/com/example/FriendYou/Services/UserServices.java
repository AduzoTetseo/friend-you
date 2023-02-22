package com.example.FriendYou.Services;

import com.example.FriendYou.DAO.UserDAO;
import com.example.FriendYou.Models.LikePostPayload;
import com.example.FriendYou.Models.Post;
import com.example.FriendYou.Models.User;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//userId is long
@Component
public class UserServices {

    @Autowired
    private UserDAO userDAO;

     //getting User ID by Email Function to call
    //  private long getUserIdByEmail(String email) throws Exception{
    //     User user = userDAO.getUserByEmail(email);
    //     if(user == null){
    //         throw new Exception("User not found");   
    //     }
    //     long userId = user.getId();
    //     return userId;
    // }

    private static final String TIME_PATTERN = "dd.mm.yyyy:hh:mm:a";


    public User addUser(User user)throws Exception {
        try {
            return userDAO.addUser(user);
        } catch (Exception e) {
            throw e;
        }
    }


   public User validateSignIn(String email, String password) throws Exception{
            
    User user = userDAO.getUserByEmail(email);

    //Checking if user exists in system
    if(user == null){
        throw new Exception("User not found!");
    }

    //Checking user password
    if(!user.getPassword().equals(password)){
        throw new Exception("Password not valid!");
    }

    //User is valid
    return user;
    }
    
    //update user info
    public User updateUser(User newUserDetails) throws Exception{

        User user = userDAO.updateUserByEmail(newUserDetails);

        if(user == null){
            throw new Exception("User not found");
        }

        return user;
    }



    //add new post service
    public Post addPost(Post newPost) throws Exception{

        //Add timestamp to post
        Instant now = Instant.now();

        //convert now to readable date string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_PATTERN).withZone(ZoneId.systemDefault());
        String formattedTime = formatter.format(now);

        newPost.setPostTimestamp(formattedTime);


    //  Timestamp instant = Timestamp.from(Instant.now());

        Post post = userDAO.addPostToDatabase(newPost);
        if(post == null){
            throw new Exception("Post Invalid");
        }
        return post;
    }

    //update post service
    public Post updatePost(Post updateUserPost) throws Exception{

        Post post= userDAO.updatePostByPostId(updateUserPost);

        //checking if user exist by checking unique key email
        if(post == null){
            throw new Exception("Post not found");
        }
        return post;
    }

    //Listing all the post currently available in the Table
    public List<Post> getPosts(Post user) throws Exception{
       return userDAO.getAllPost(user);
    }

    //Checking for likes and dislikes on post to increment and decrement
    public Post likeDislikePost(LikePostPayload payload)throws Exception{

        //Get existing like counts
        Post post = userDAO.getPostById(payload.getPostId());
        long likeCount = post.getLikeDislike();

        //Increment/Decrement likes based on like or disliked
        if(payload.isLike()){
            likeCount++;
        }else{
            likeCount--;
        }

        //Update new value to database
        userDAO.updateLikeCountByPostId(likeCount, payload.getPostId());
        post.setLikeDislike(likeCount);
        return post;
    }

}

    
