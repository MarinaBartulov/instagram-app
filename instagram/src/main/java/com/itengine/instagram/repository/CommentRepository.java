package com.itengine.instagram.repository;

import com.itengine.instagram.model.Comment;
import com.itengine.instagram.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value="select c from Comment c where c.post.id=?1")
    List<Comment> findCommentsForPost(Long id);
}
