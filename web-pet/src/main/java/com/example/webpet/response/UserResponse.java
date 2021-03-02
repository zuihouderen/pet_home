package com.example.webpet.response;

import com.example.webpet.entity.User;

public class UserResponse {
    private User user;
    private String message;
    private int success;

    public UserResponse(User user,String message,int success){
        this.message=message;
        this.success=success;
        this.user=user;
    }

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
