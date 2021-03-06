package com.itengine.instagram.service;

import com.itengine.instagram.dto.*;
import com.itengine.instagram.model.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);
    User save(UserRequest userRequest);
    UserDTO getCurrentUser();
    UserDTO findUser(Long id);
    User update(User user);
    List<UserAdminDTO> getAllUsers();
    UserDTO deleteUser(Long id);
    User findById(Long id);
    List<UserResponseDTO> getFollowersForUser(Long id);
    List<UserResponseDTO> getFollowingForUser(Long id);
    List<UserFollowDTO> searchUsers(String username);
    UserProfileDetailsDTO getUserProfileDetails(Long id);
    UserDTO banUser(Long id);
}
