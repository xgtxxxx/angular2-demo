package com.b2s.scrumlr.odoo.model;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private String lang = "en_US";
    private boolean tz = false;
    private Boolean bin_size = true;
    private Boolean active_test;
    private Integer uid;
    private Integer employee_id;
    private Integer user_id;
    private String timesheet_date_from;
    private String timesheet_date_to;
    private Integer default_use_timesheets;
    private String default_type;
    private Integer account_id;
    private Map<String, Object> params;

    public void addParam(final String key, final Object value){
        if(this.params==null){
            this.params = new HashMap<>();
        }
        this.params.put(key,value);
    }
    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Boolean getActive_test() {
        return active_test;
    }

    public void setActive_test(Boolean active_test) {
        this.active_test = active_test;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public boolean isTz() {
        return tz;
    }

    public void setTz(boolean tz) {
        this.tz = tz;
    }

    public Boolean getBin_size() {
        return bin_size;
    }

    public void setBin_size(Boolean bin_size) {
        this.bin_size = bin_size;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getTimesheet_date_from() {
        return timesheet_date_from;
    }

    public void setTimesheet_date_from(String timesheet_date_from) {
        this.timesheet_date_from = timesheet_date_from;
    }

    public String getTimesheet_date_to() {
        return timesheet_date_to;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public Integer getDefault_use_timesheets() {
        return default_use_timesheets;
    }

    public void setDefault_use_timesheets(Integer default_use_timesheets) {
        this.default_use_timesheets = default_use_timesheets;
    }

    public String getDefault_type() {
        return default_type;
    }

    public void setDefault_type(String default_type) {
        this.default_type = default_type;
    }

    public void setTimesheet_date_to(String timesheet_date_to) {
        this.timesheet_date_to = timesheet_date_to;
    }
}
