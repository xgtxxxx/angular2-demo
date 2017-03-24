package com.b2s.scrumlr.admin.model;

import com.b2s.scrumlr.odoo.model.TimesheetTask;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class AdminUser extends BaseUser {
    @JsonView(WithoutPasswordView.class)
    private boolean active;
    @JsonView(WithoutPasswordView.class)
    private String mailAddress;
    @JsonView(WithoutPasswordView.class)
    private List<TimesheetTask> timesheetTasks;

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(final String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public List<TimesheetTask> getTimesheetTasks() {
        return timesheetTasks;
    }

    public void setTimesheetTasks(final List<TimesheetTask> timesheetTasks) {
        this.timesheetTasks = timesheetTasks;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public interface WithoutPasswordView {};
    public interface WithPasswordView extends WithoutPasswordView {};
}
