package com.myblog.myblog.Controller;

import com.myblog.myblog.Payload.CommentDto;
import com.myblog.myblog.Service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    CommentService commentService;


    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
//http//localhost:8080/api/posts/2/comments
    @PostMapping("posts/{postId}/comments")
public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId, @RequestBody CommentDto commentDto){
        CommentDto commentDto1= commentService.createComment(postId, commentDto);

return  new ResponseEntity<>(commentDto1, HttpStatus.CREATED);
    }
    //http//:locallost:8080/api/posts/postId/commemnts
    @GetMapping("/posts/{postId}/comments")//this method get my postId if post is there check then based on post u get all coomments
    public List<CommentDto> getCommentsByPostById(@PathVariable(value = "postId")long postId
                                                  ){
        return  commentService.getCommentsByPostId(postId);
    }
//here based on  comment id get coment by id
   @GetMapping("posts/{postId}/comments/{id}")
    public  ResponseEntity<CommentDto> getByCommentId(@PathVariable(value = "postId")long postId,
                                                      @PathVariable(value = "id")long commentId)
   {
      return new ResponseEntity<>(commentService.getCommentById(postId,commentId),HttpStatus.OK) ;
   }

   //http//:localhost:8080/api/posts/postId/comments/id

   @PutMapping("posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateCommentBYid(
             @PathVariable(value = "postId")long postId,
             @PathVariable(value = "id")long id,
             @RequestBody CommentDto commentdto
   ){
       CommentDto updateDto = commentService.updateCommentById(postId, id, commentdto);

       return new ResponseEntity<>(updateDto,HttpStatus.OK) ;
   }

   //http://localhost:8080/api/posts/postId/comments/id
@DeleteMapping("posts/{postId}/comment/{id}")//dlete by cooment id if post is check then post belongs to that commnet
    public ResponseEntity<String> deleteCommentById(@PathVariable (value = "postId")long postId,
                                                    @PathVariable(value="id") long id)

{
    commentService.deletecommentById(postId,id);


return new ResponseEntity<>("delete record is successfully",HttpStatus.OK);
}
















}
