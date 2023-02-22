package com.example.FriendYou.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private String post;
    private String postedBy;
    private String postTimestamp; //dd-mm-yyyy:hh:mm:aa -> 12-02-2023:03:23:pm

    private long likeDislike;
    private long postId;

    private long userId;

}
