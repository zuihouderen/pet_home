package com.example.webpet.response;


import com.example.webpet.entity.Pdkeep;

import java.util.List;

public class PdkeepResponse {
    private Pdkeep pdkeep;
    private String message;
    private int success;
    private List<Pdkeep> pdkeeps;

    public PdkeepResponse(Pdkeep pdkeep, String message, int success){
        this.message=message;
        this.success=success;
        this.pdkeep=pdkeep;
    }
    public PdkeepResponse(List<Pdkeep> pdkeeps, String message, int success){
        this.message=message;
        this.success=success;
        this.pdkeeps=pdkeeps;
    }

    public List<Pdkeep> getPdkeeps() {
        return pdkeeps;
    }

    public void setPdkeeps(List<Pdkeep> pdkeeps) {
        this.pdkeeps = pdkeeps;
    }

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Pdkeep getPdkeep() {
        return pdkeep;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setPdkeep(Pdkeep pdkeep) {
        this.pdkeep = pdkeep;
    }
}
