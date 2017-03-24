package com.b2s.scrumlr.admin.model;

import java.util.List;

public class AdminLogTask {
    private String date;
    private List<String> userIds;

    public String getDate() {
        return date;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(final List<String> userIds) {
        this.userIds = userIds;
    }
}
