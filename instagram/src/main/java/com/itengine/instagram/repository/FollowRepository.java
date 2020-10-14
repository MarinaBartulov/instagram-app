package com.itengine.instagram.repository;

import com.itengine.instagram.model.Follow;
import com.itengine.instagram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {


    Follow findByFollowedAndFollower(User followed, User follower);
}
