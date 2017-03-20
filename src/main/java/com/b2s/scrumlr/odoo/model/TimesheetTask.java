package com.b2s.scrumlr.odoo.model;

public class TimesheetTask {
    private String project;

    private String projectName;

    private String taskName;
    private String task;
    private String currentDate;
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
