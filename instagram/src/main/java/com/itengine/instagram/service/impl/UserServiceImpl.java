package com.itengine.instagram.service.impl;

import com.itengine.instagram.dto.UserRequest;
import com.itengine.instagram.model.Authority;
import com.itengine.instagram.model.User;
import com.itengine.instagram.repository.AuthorityRepository;
import com.itengine.instagram.repository.UserRepository;
import com.itengine.instagram.service.AuthorityService;
import com.itengine.instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public User save(UserRequest userRequest) {

        Authority a = this.authorityService.findByName("ROLE_USER");
        List<Authority> authorities =  Arrays.asList(a);
        User user = User.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .name(userRequest.getName())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .authorities(authorities)
                .active(true)
                .build();

        return this.userRepository.save(user);
    }
}
