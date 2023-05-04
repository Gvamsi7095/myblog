package com.myblog.myblog.Controller;

import com.myblog.myblog.Payload.PostDto;
import com.myblog.myblog.Payload.PostResponse;
import com.myblog.myblog.Service.PostService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/post")//
// /common for all methods
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    //http://localhost:8080/api/post
    @PreAuthorize("hasRole('ADMIN')")///Login adimin try to create post able  to create tyhithis functinality can be acces only u have log in as user
    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult result) { //return status  we want return
       if(result.hasErrors()){
         return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR)  ;
       }

        PostDto dto = postService. createPost(postDto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);//201 status code return code


    }

    //http://localhost:8080/api/post/?pageNo=0&pageSize=10&sortBy=title&sortDir=asc sorting will happen in ascending order //bases on title
@GetMapping
    public ResponseEntity<PostResponse>getAllMyId(
        @RequestParam(value = "pageNo",defaultValue= "0",required = false)int pageNo,
        @RequestParam(value="pageSize",defaultValue = "10",required = false)int pageSize,
        @RequestParam(value="sortBy",defaultValue = "id",required = false) String sortBy,
        @RequestParam(value ="sortDir",defaultValue = "asc",required = false)String sortDir
)

{
    PostResponse postResponse = postService.getAllMyId(pageNo, pageSize, sortBy, sortDir);

    return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }//ht tp://localhost:808/api/post/id
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable ("id") long id){
        PostDto dto = postService.getById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);///http status return back to api  mean postman
    }

    //http://localhost:8080/api/posts/1 //////through these url json object will also go to the controller handler methods
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
                                          @PathVariable("id")long id){
        PostDto postResponse = postService.updatePost(postDto, id);

return  new ResponseEntity<>(postResponse,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public  ResponseEntity<String>deleteById(@PathVariable("id")long id){



        postService.deleteById(id);

        return new ResponseEntity<String>("deleted the record successfuly ",HttpStatus.OK);

    }








}
