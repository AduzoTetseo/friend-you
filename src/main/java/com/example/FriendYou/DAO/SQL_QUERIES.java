package com.example.FriendYou.DAO;

public class SQL_QUERIES {
    public static final String INSERT_NEW_USER = "INSERT INTO users(username, email, password) VALUES (?,?,?)";

    public static final String VALIDATE_USER_LOGIN = "SELECT * FROM USER WHERE username = ? AND password like ?";
}
