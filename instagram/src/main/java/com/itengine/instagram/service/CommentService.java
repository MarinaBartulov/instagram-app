package com.itengine.instagram.service;

import com.itengine.instagram.dto.CommentAdminDTO;
import com.itengine.instagram.dto.CommentDTO;
import com.itengine.instagram.dto.CommentNewDTO;

import java.util.List;

public interface CommentService {

      CommentDTO getComment(Long id);
      List<CommentDTO> getCommentsForPost(Long id);
      CommentDTO addCommentForPost(CommentNewDTO commentNewDTO);
      CommentDTO deleteComment(Long id);
      List<CommentAdminDTO> getAllComments();
      CommentDTO deleteCommentAdmin(Long id);
}
