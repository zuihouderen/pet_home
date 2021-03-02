package com.example.webplog.response;




import com.example.webpet.entity.Plog;

import java.util.List;

public class PlogResponse {
    private Plog plog;
    private String message;
    private int success;
    private List<Plog>plogs;

    public PlogResponse(Plog plog, String message, int success){
        this.message=message;
        this.success=success;
        this.plog=plog;
    }
    public PlogResponse(List<Plog> plogs, String message, int success){
        this.message=message;
        this.success=success;
        this.plogs=plogs;
    }

    public List<Plog> getPlogs() {
        return plogs;
    }

    public void setPlogs(List<Plog> plogs) {
        this.plogs = plogs;
    }

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Plog getPlog() {
        return plog;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setPlog(Plog plog) {
        this.plog = plog;
    }
}
