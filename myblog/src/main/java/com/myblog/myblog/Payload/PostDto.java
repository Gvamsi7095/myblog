package com.myblog.myblog.Payload;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data


public class PostDto {
private  long id;

@NotEmpty//if u not give title it will give error
@Size(min =2,message = " post title should have at least  2 charters  ")
private String title;
@NotEmpty
@Size(min=10,message = "post description should have at least 10 characters ")
private String description;
@NotEmpty
private String content;




}
