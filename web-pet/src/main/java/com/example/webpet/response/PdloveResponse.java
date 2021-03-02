package com.example.webpet.response;


import com.example.webpet.entity.Pdlove;

import java.util.List;

public class PdloveResponse {
    private Pdlove pdlove;
    private String message;
    private int success;
    private List<Pdlove> pdloves;

    public PdloveResponse(Pdlove pdlove, String message, int success){
        this.message=message;
        this.success=success;
        this.pdlove=pdlove;
    }
    public PdloveResponse(List<Pdlove> pdloves, String message, int success){
        this.message=message;
        this.success=success;
        this.pdloves=pdloves;
    }

    public List<Pdlove> getPdloves() {
        return pdloves;
    }

    public void setPdloves(List<Pdlove> pdloves) {
        this.pdloves = pdloves;
    }

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Pdlove getPdlove() {
        return pdlove;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setPdlove(Pdlove pdlove) {
        this.pdlove = pdlove;
    }
}
