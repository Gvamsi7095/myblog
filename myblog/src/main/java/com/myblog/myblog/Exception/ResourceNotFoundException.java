package com.myblog.myblog.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class ResourceNotFoundException extends RuntimeException {




    private String resourceName;//table name  post
    private String    fieldName;                // in the table with field id  /post id with id
    private long fieldValue;////with id filed name value of id


    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
super(String.format("%S is not found with %s:'%S'",resourceName,fieldName,fieldValue));
//super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue//automatically dyanamicalay gores
        this.resourceName = resourceName;
        this.fieldName = fieldName;//act like setter
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public long getFieldValue()
    {
        return fieldValue;
    }
}
