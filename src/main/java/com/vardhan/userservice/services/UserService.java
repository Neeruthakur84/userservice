package com.vardhan.userservice.services;


import com.vardhan.userservice.models.Token;
import com.vardhan.userservice.models.User;

public interface UserService {

     User signup(String name, String email, String password);
     Token login(String email, String password);
     void logout(Token tokenValue);
     User validateToken(String tokenValue);

}
