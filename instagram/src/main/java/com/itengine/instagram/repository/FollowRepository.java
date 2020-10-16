package com.itengine.instagram.repository;

import com.itengine.instagram.model.Follow;
import com.itengine.instagram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {


    //@Query("select fo from Follow f inner join f.follower fo inner join f.followed fl where fl.id = ?1")
    @Query("select f.follower from Follow f where f.followed.id = ?1")
    List<User> getFollowersForUser(Long id);

    //@Query("select fl from Follow f inner join f.followed fl inner join f.follower fo where fo.id = ?1")
    @Query("select f.followed from Follow f where f.follower.id = ?1")
    List<User> getFollowingForUser(Long id);

    Follow findByFollowedAndFollower(User followed, User follower);
}
