package com.itengine.instagram.repository;

import com.itengine.instagram.model.Like;
import com.itengine.instagram.model.Post;
import com.itengine.instagram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {


    Like findByPostAndUser(Post post, User user);
}
