package com.myblog.myblog.Service;



import com.myblog.myblog.Payload.CommentDto;
import com.myblog.myblog.Payload.PostDto;
import com.myblog.myblog.Payload.PostResponse;

import java.util.List;

public interface PostService {



    PostDto createPost(PostDto postDto);


   PostResponse getAllMyId(int pageNo, int pageSize, String sortBy, String sortDir);


    PostDto getById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deleteById(long id);

}
