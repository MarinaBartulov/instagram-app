package com.itengine.instagram.repository;

import com.itengine.instagram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query("select fo from Follow  f inner join f.follower fo inner join f.followed fl where fl.id = ?1")
    List<User> getFollowersForUser(Long id);

    @Query("select fl from Follow  f inner join f.followed fl inner join f.follower fo where fo.id = ?1")
    List<User> getFollowingForUser(Long id);

    @Query("from User where username like %?1% and id <> ?2")
    List<User> searchUsers(String username, Long id);

}
