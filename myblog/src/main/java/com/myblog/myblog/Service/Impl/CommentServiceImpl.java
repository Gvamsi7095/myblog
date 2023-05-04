package com.myblog.myblog.Service.Impl;

import com.myblog.myblog.Entity.Comment;
import com.myblog.myblog.Entity.Post;
import com.myblog.myblog.Exception.BlogAPIException;
import com.myblog.myblog.Exception.ResourceNotFoundException;
import com.myblog.myblog.Payload.CommentDto;
import com.myblog.myblog.Repository.CommentRepository;
import com.myblog.myblog.Repository.PostRepository;
import com.myblog.myblog.Service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

   private  PostRepository postRepository;
 private   CommentRepository commentRepository;
 private ModelMapper modelMapper;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository,ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {



        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId)


        );
        Comment comment = mapToEntity(commentDto);//get comment based on comment
        comment.setPost(post);
        Comment savedComment= commentRepository.save(comment);//auto generated in comment
        CommentDto commentDto1 = mapToDto(savedComment);
        return commentDto1;
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long posId) {

        Post post = postRepository.findById(posId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", posId)
        );

        List<Comment> comments = commentRepository.findByPostId(posId);


        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());




    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment", "id", commentId)
        );
        if (comment.getPost().getId()!=(post.getId())){

            throw  new BlogAPIException(HttpStatus.BAD_REQUEST,"comment is not belong to post");
        }


        return mapToDto(comment);
    }

    @Override
    public CommentDto updateCommentById(long postId, long id, CommentDto commentdto) {
        Post post = postRepository.findById(postId).orElseThrow(

                () -> new ResourceNotFoundException("post", "id", postId)
        );
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("comment", "id", id)
        );

        if(comment.getPost().getId()!=post.getId()){

            throw  new BlogAPIException(HttpStatus.BAD_REQUEST,"comment is not belonged to that post");
        }
        comment.setName(commentdto.getName());
        comment.setBody(commentdto.getBody());
        comment.setEmail(commentdto.getEmail());
        Comment updatedComment = commentRepository.save(comment);
        CommentDto updateDto = mapToDto(updatedComment);
        return updateDto;
    }

    @Override
    public void deletecommentById(long postId, long id) {
        Post post = postRepository.findById(postId).orElseThrow(

                () -> new ResourceNotFoundException("post", "id", postId)
        );
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("comment", "id", id)
        );

        if(comment.getPost().getId()!=post.getId()){
            throw  new BlogAPIException(HttpStatus.BAD_REQUEST,"comment not belong to that post");
        }
        commentRepository.deleteById(id);
    }

    private     CommentDto mapToDto(Comment savedComment) {

        CommentDto commentDto1 = modelMapper.map(savedComment, CommentDto.class);


//        CommentDto commentDto1 = new CommentDto();
//        commentDto1.setId(savedComment.getId());
//        commentDto1.setName(savedComment.getName());
//
//        commentDto1.setBody(savedComment.getBody());
//commentDto1.setEmail(savedComment.getEmail());
return commentDto1;
    }

    private Comment mapToEntity( CommentDto commentDto) {
        Comment  comment = modelMapper.map(commentDto, Comment.class);
//        Comment comment =new Comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setBody(commentDto.getBody());
//        comment.setEmail(commentDto.getEmail());
       return  comment;
    }
}
