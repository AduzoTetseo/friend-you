package com.example.FriendYou.DAO;

import com.example.FriendYou.Models.Post;
import com.example.FriendYou.Models.User;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class UserDAO {

    public Long counter;

    @Autowired
    private  JdbcTemplate jdbcTemplate;

    public User addUser(User user) throws Exception{
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection
                  .prepareStatement("INSERT INTO users(username, email, password) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
                  ps.setString(1, user.getUsername());
                  ps.setString(2, user.getEmail());
                  ps.setString(3, user.getPassword());
                  return ps;
                }, keyHolder);
        
                long userId = keyHolder.getKey().longValue();
                user.setId(userId);
                return user;
            }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }finally{
            System.out.println("At finally block");
        }
    }


    public User getUserByEmail(String email){
        Object[] obj = new Object[] {email};
        String query= "SELECT * FROM users WHERE email = ? ";
        List<User> users = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(User.class), obj);
        if(users.size() > 0){
            return users.get(0);
        }
        return null;
    }

    //DAO method to update username and password
    public User updateUserByEmail(User newUserDetails){
        try{
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection
                  .prepareStatement("UPDATE users SET username = ? , password = ? WHERE email = ?");
                  ps.setString(1, newUserDetails.getUsername());
                  ps.setString(2, newUserDetails.getPassword());
                  ps.setString(3, newUserDetails.getEmail());
                  return ps;
                });
                return newUserDetails;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    //DAO method to add new post using email
    public Post addPostToDatabase(Post newPost) throws Exception{
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection
                  .prepareStatement("INSERT INTO post_data (post, user_id, post_timestamp) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
                  ps.setString(1, newPost.getPost());
                  ps.setLong(2, newPost.getUserId());
                  ps.setString(3, newPost.getPostTimestamp());
                  return ps;
                }, keyHolder);
                long postId = keyHolder.getKey().longValue();
                newPost.setPostId(postId);
                // newPost.setPostTimestamp(Instant.now());
                return newPost; 
            }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }finally{
            System.out.println("At finally block");
        }
    }

    public Post updatePostByPostId(Post updatePost) throws Exception{
        try{
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection
                  .prepareStatement("UPDATE post_data SET post = ? WHERE post_id = ?");
                  ps.setString(1, updatePost.getPost());
                  ps.setLong(2, updatePost.getPostId());
                  return ps;
                });

        return updatePost;
    }catch (Exception e){
        e.printStackTrace();
        throw e;
    }finally{
        System.out.println("At finally block");
    }
}


    public List<Post> getAllPost(Post user) throws Exception{
        try{
            
            String query = "SELECT * FROM post_data";
            List <Post> posts = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Post.class));
            return posts;
        } catch (Exception e){
            throw e;
        }
    }

    public void updateLikeCountByPostId(long newLikesCount, long postId) throws Exception{
            try{
                jdbcTemplate.update(connection -> {
                    PreparedStatement ps = connection
                      .prepareStatement("UPDATE post_data SET like_dislike = ? WHERE post_id = ?");
                      ps.setLong(1,newLikesCount);
                      ps.setLong(2, postId);
                      return ps;
            });
        }catch (Exception e){
            throw e;
        }
    }

    public Post getPostById(long postId) throws Exception{
        Object[] obj = new Object[] {postId};
        String query= "SELECT * FROM post_data WHERE post_id = ? ";
        List<Post> posts = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Post.class), obj);
        if(posts.size() > 0){
            return posts.get(0);
        }
        throw new Exception("Post Not found!");
    }
}