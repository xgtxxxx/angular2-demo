package com.b2s.scrumlr.odoo.model;

import java.util.ArrayList;
import java.util.List;

public class OdooLog {
    private String user;
    private Boolean success;
    private Boolean isLoged = false;
    private List<LogBody> bodys = new ArrayList<>();

    public OdooLog(final String user, final Boolean success, final Boolean isLoged){
        this.success = success;
        this.isLoged = isLoged;
        this.user = user;
    }

    public void addLogBody(final LogBody body){
        if(bodys==null){
            bodys = new ArrayList<>();
        }
        bodys.add(body);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getIsLoged() {
        return isLoged;
    }

    public void setLoged(Boolean loged) {
        isLoged = loged;
    }

    public List<LogBody> getBodys() {
        return bodys;
    }

    public void setBodys(List<LogBody> bodys) {
        this.bodys = bodys;
    }
}
