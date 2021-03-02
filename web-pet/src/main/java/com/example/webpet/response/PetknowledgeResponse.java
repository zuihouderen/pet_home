package com.example.webpet.response;


import com.example.webpet.entity.Petknowledge;

import java.util.List;

public class PetknowledgeResponse {
    private Petknowledge petKnowledge;
    private String message;
    private int success;
    private List<Petknowledge> petKnowledges;

    public PetknowledgeResponse(Petknowledge petKnowledge, String message, int success){
        this.message=message;
        this.success=success;
        this.petKnowledge=petKnowledge;
    }
    public PetknowledgeResponse(List<Petknowledge> petKnowledges, String message, int success){
        this.message=message;
        this.success=success;
        this.petKnowledges=petKnowledges;
    }

    public List<Petknowledge> getPetknowledges() {
        return petKnowledges;
    }

    public void setPetknowledges(List<Petknowledge> petKnowledges) {
        this.petKnowledges = petKnowledges;
    }

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Petknowledge getPetknowledge() {
        return petKnowledge;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setPetknowledge(Petknowledge petKnowledge) {
        this.petKnowledge = petKnowledge;
    }
}
