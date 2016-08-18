package com.b2s.scrumlr.odoo.model;

import java.util.List;

public class OdooResults {
    private String username;
    private Integer uid;
    private String session_id;
    private Integer res_id;
    private Object[] employee_id;
    private List timesheet_ids;
    private String date_from;
    private String date_to;
    private String date;
    private Object[] task_id;
    private Object[] parent_analytic_id;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String state;

    public Double getUnit_amount() {
        return unit_amount;
    }

    public void setUnit_amount(Double unit_amount) {
        this.unit_amount = unit_amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Object[] getTask_id() {
        return task_id;
    }

    public void setTask_id(Object[] task_id) {
        this.task_id = task_id;
    }

    public Object[] getParent_analytic_id() {
        return parent_analytic_id;
    }

    public void setParent_analytic_id(Object[] parent_analytic_id) {
        this.parent_analytic_id = parent_analytic_id;
    }

    private Double unit_amount;

    public Object[] getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Object[] employee_id) {
        this.employee_id = employee_id;
    }

    public List getTimesheet_ids() {
        return timesheet_ids;
    }

    public void setTimesheet_ids(List timesheet_ids) {
        this.timesheet_ids = timesheet_ids;
    }

    public String getDate_from() {
        return date_from;
    }

    public void setDate_from(String date_from) {
        this.date_from = date_from;
    }

    public String getDate_to() {
        return date_to;
    }

    public void setDate_to(String date_to) {
        this.date_to = date_to;
    }

    public Integer getRes_id() {
        return res_id;
    }

    public void setRes_id(Integer res_id) {
        this.res_id = res_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }
}
