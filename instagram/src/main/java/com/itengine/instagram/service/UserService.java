package com.itengine.instagram.service;

import com.itengine.instagram.dto.UserDTO;
import com.itengine.instagram.dto.UserRequest;
import com.itengine.instagram.model.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);
    User save(UserRequest userRequest);
    UserDTO getCurrentUser();
    UserDTO findUser(Long id);
    User update(User user);
    List<UserDTO> getAllUsers();
    UserDTO deleteUser(Long id);
}
