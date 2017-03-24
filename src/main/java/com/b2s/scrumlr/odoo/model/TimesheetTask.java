package com.b2s.scrumlr.odoo.model;

import com.b2s.scrumlr.admin.model.AdminUser;
import com.fasterxml.jackson.annotation.JsonView;

public class TimesheetTask {
    @JsonView(AdminUser.WithoutPasswordView.class)
    private String project;

    private String projectName;

    private String taskName;
    @JsonView(AdminUser.WithoutPasswordView.class)
    private String task;
    private String currentDate;
    @JsonView(AdminUser.WithoutPasswordView.class)
    private double hours;
    private Integer projectId;

    private Integer parentAnalyticId;

    private Integer taskId;

    private Integer stageId;

    private Integer accountId;

    public String getProject() {
        return project;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public double getHours() {
        return hours;
    }

    public Integer getParentAnalyticId() {
        return parentAnalyticId;
    }

    public void setParentAnalyticId(Integer parentAnalyticId) {
        this.parentAnalyticId = parentAnalyticId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }
}
