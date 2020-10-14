package com.itengine.instagram.service.impl;

import com.itengine.instagram.dto.UserDTO;
import com.itengine.instagram.dto.UserRequest;
import com.itengine.instagram.dto.UserResponseDTO;
import com.itengine.instagram.exception.BadRequestException;
import com.itengine.instagram.exception.NotFoundException;
import com.itengine.instagram.model.Authority;
import com.itengine.instagram.model.User;
import com.itengine.instagram.repository.AuthorityRepository;
import com.itengine.instagram.repository.UserRepository;
import com.itengine.instagram.service.AuthorityService;
import com.itengine.instagram.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private ModelMapper mapper;

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

    @Override
    public UserDTO getCurrentUser() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        User user = this.userRepository.findByUsername(username);
        return this.mapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO findUser(Long id) {
        if(id <= 0)
            throw new BadRequestException("Id can't be equal or less than 0.");
        User user = this.userRepository.findById(id).orElse(null);
        if(user == null){
            throw new NotFoundException("User with id " + id + " not found.");
        }
        return this.mapper.map(user, UserDTO.class);

    }

    @Override
    public User update(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> users = this.userRepository.findAll();
        return users.stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO deleteUser(Long id) {

        if(id <= 0){
            throw new BadRequestException("User id can't be equal or less than 0.");
        }

        User user = this.userRepository.findById(id).orElse(null);
        if(user == null){
            throw new NotFoundException("User with id " + id + " doesn't exist.");
        }

        user.setDeleted(true);
        this.userRepository.save(user);
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserResponseDTO> getFollowersForUser(Long id) {

        List<User> followers = this.userRepository.getFollowersForUser(id);
        return followers.stream().map(user -> new UserResponseDTO(user)).collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDTO> getFollowingForUser(Long id) {

        List<User> following = this.userRepository.getFollowingForUser(id);
        return following.stream().map(user -> new UserResponseDTO(user)).collect(Collectors.toList());
    }
}
