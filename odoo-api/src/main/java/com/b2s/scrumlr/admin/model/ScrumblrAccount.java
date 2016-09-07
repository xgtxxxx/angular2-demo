package com.b2s.scrumlr.admin.model;

import java.util.List;

public class ScrumblrAccount {
    private String uname;
    private String pwd;
    private String mail;
    private int points;
    private int authority;
    private List<SubAttention> subs;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public List<SubAttention> getSubs() {
        return subs;
    }

    public void setSubs(List<SubAttention> subs) {
        this.subs = subs;
    }
}
