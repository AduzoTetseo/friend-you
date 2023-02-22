package com.example.FriendYou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.FriendYou")
public class FriendYouApplication {

	public static void main(String[] args) {

		SpringApplication.run(FriendYouApplication.class, args);
	}

}
