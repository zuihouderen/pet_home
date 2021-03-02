package com.example.webpet.response;

import com.example.webpet.entity.Message;
import java.util.List;

public class MessageResponse {
    private Message mmessage;
    private String message;
    private int success;
    private List<Message>messages;

    public MessageResponse(Message mmessage, String message, int success){
        this.mmessage=mmessage;
        this.success=success;
        this.message=message;
    }
    public MessageResponse(List<Message> messages, String message, int success){
        this.message=message;
        this.success=success;
        this.messages=messages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Message getMmessage() {
        return mmessage;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setMmessage(Message mmessage) {
        this.mmessage = mmessage;
    }
}
