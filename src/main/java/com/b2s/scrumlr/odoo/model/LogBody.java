package com.b2s.scrumlr.odoo.model;

public class LogBody {
    private double hours;
    private String project;
    private String task;

    public LogBody(final double hours, final String project, final String task){
        this.hours = hours;
        this.project = project;
        this.task = task;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public String getProject() {
        return project;
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
}
