package com.myblog.myblog.Repository;

import com.myblog.myblog.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment ,Long> {


   List<Comment> findByPostId(long PostId);


}
