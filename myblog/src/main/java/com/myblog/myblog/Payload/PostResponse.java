package com.myblog.myblog.Payload;

import com.myblog.myblog.Entity.Post;
import javafx.geometry.Pos;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {


    private List<PostDto> content;
    private int pageNo;
    private int pageSize;
    private int totalElements;
    private int totalPages;
    private Boolean isLast;
}



