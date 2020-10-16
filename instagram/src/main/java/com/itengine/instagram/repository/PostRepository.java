package com.itengine.instagram.repository;

import com.itengine.instagram.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {


    @Query(value="select p from Post p where p.user.id=?1")
    List<Post> findUserPosts(Long id);

    @Query(value="select p from User u inner join u.following fo inner join fo.followed fl inner join fl.posts p where u.id = ?1 order by p.dateTime DESC")
    List<Post> getUserPostsFeed(Long id);

}
