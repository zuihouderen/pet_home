package com.example.webpet.response;


import com.example.webpet.entity.Pdynamic;

import java.util.List;

public class PdynamicResponse {
    private Pdynamic pdynamic;
    private String message;
    private int success;
    private List<Pdynamic> pdynamics;

    public PdynamicResponse(Pdynamic pdynamic, String message, int success){
        this.message=message;
        this.success=success;
        this.pdynamic=pdynamic;
    }
    public PdynamicResponse(List<Pdynamic> pdynamics, String message, int success){
        this.message=message;
        this.success=success;
        this.pdynamics=pdynamics;
    }

    public List<Pdynamic> getPdynamics() {
        return pdynamics;
    }

    public void setPdynamics(List<Pdynamic> pdynamics) {
        this.pdynamics = pdynamics;
    }

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Pdynamic getPdynamic() {
        return pdynamic;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setPdynamic(Pdynamic pdynamic) {
        this.pdynamic = pdynamic;
    }
}
