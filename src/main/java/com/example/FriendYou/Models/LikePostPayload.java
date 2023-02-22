package com.example.FriendYou.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikePostPayload {
    private long postId;
    private long userId;
    private boolean like;
}
