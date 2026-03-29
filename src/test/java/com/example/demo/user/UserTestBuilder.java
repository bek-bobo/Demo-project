package com.example.demo.user;


import com.example.demo.user.entity.User;
import com.example.demo.user.entity.UserStatus;

import java.util.UUID;

public class UserTestBuilder {
    private final User user;

    private UserTestBuilder(){
        user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername("Test User");
        user.setPassword("password");
        user.setEmail("test@mail.com");
        user.setPhoneNumber("998901234567");
        user.setUserStatus(UserStatus.WAITING);
    }

    public static UserTestBuilder newUser(){
        return new UserTestBuilder();
    }

    public  UserTestBuilder withId(UUID id){
        user.setId(id);
        return this;
    }

    public UserTestBuilder withUsername(String username) {
        user.setUsername(username);
        return this;
    }

    public User build(){
        return user;
    }
}
