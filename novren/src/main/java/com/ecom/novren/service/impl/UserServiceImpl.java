package com.ecom.novren.service.impl;

import com.ecom.novren.config.JwtProvider;
import com.ecom.novren.model.User;
import com.ecom.novren.repository.UserRepository;
import com.ecom.novren.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email=jwtProvider.getEmailFromJwtToken(jwt);

        User user = this.findUserByEmail(email);

        if (user==null){
            throw new Exception("user not found with email "+email);
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user=userRepository.findByEmail(email);
        if (user==null){
            throw new Exception("user not found with email");
        }
        return user;
    }
}
