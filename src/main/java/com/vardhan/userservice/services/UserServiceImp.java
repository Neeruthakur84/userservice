package com.vardhan.userservice.services;


import com.vardhan.userservice.models.Token;
import com.vardhan.userservice.models.User;
import com.vardhan.userservice.repositories.TokenRepository;
import com.vardhan.userservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    TokenRepository tokenRepository;
    public UserServiceImp(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public User signup(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }

    @Override
    public Token login(String email, String password) {

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return null;
        }
        User user = optionalUser.get();
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return null;
        }

        Token token = new Token();
        token.setUser(user);
      //  token.setValue(UUID.randomUUID().toString());
        token.setValue(RandomStringUtils.randomAlphanumeric(128));

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 30);
        Date date = calendar.getTime();
        token.setExpiryAt(date);

        tokenRepository.save(token);

        return token;
    }

    @Override
    public void logout(Token tokenValue) {

    }

    @Override
    public User validateToken(String tokenValue) {

         Optional<Token> optionalToken =  tokenRepository.findByValueAndDeletedAndExpiryAtGreaterThan(tokenValue, false, new Date());

        return optionalToken.map(Token::getUser).orElse(null);

    }
}
