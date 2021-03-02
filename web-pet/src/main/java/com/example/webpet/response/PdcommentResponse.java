package com.example.webpet.response;


import com.example.webpet.entity.Pdcomment;
import com.example.webpet.entity.Pdcomment;

import java.util.List;

public class PdcommentResponse {
    private Pdcomment pdcomment;
    private String message;
    private int success;
    private List<Pdcomment> pdcomments;

    public PdcommentResponse(Pdcomment pdcomment, String message, int success){
        this.message=message;
        this.success=success;
        this.pdcomment=pdcomment;
    }
    public PdcommentResponse(List<Pdcomment> pdcomments, String message, int success){
        this.message=message;
        this.success=success;
        this.pdcomments=pdcomments;
    }

    public List<Pdcomment> getPdcomments() {
        return pdcomments;
    }

    public void setPdcomments(List<Pdcomment> pdcomments) {
        this.pdcomments = pdcomments;
    }

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Pdcomment getPdcomment() {
        return pdcomment;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setPdcomment(Pdcomment pdcomment) {
        this.pdcomment = pdcomment;
    }
}
