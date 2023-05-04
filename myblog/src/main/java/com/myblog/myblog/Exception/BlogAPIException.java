package com.myblog.myblog.Exception;

import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException{

    private HttpStatus status;
    private String message;

    public BlogAPIException(HttpStatus status,String message) {

        super(message);
this.status=status;///seeter
this.message=message;//setter

    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
