package com.example.mongodb;

import java.util.ArrayList;
import java.util.List;

public class CustomizedResponse<T> {

    private String message;
    //private List<T> body;
    private T body;

    public CustomizedResponse() {
    }



    public CustomizedResponse(String message, T object) {
        this.message = message;
        this.body = object;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }


}
