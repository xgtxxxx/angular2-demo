package com.b2s.scrumlr.odoo.model;

import java.util.List;

public class TaskRequest {
    private List<User> users;
    private String date;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
