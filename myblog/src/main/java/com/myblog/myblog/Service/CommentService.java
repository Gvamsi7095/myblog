package com.myblog.myblog.Service;

import com.myblog.myblog.Payload.CommentDto;

import java.util.List;

public interface CommentService {
  public   CommentDto createComment(long postId, CommentDto commentDto);
  public List<CommentDto>getCommentsByPostId(long posId);

  CommentDto getCommentById(Long postId, Long commentId);


  CommentDto  updateCommentById(long postId, long id, CommentDto commentdto);

  void deletecommentById(long postId, long id);
}
