package com.b2s.scrumlr.admin.model;

public class ScrumblrUser {
    private String name;
    private String account;
    private String password;
    private String defaultTask;
    private Customs customs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDefaultTask() {
        return defaultTask;
    }

    public void setDefaultTask(String defaultTask) {
        this.defaultTask = defaultTask;
    }

    public Customs getCustoms() {
        return customs;
    }

    public void setCustoms(Customs customs) {
        this.customs = customs;
    }
}
