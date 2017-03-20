package com.b2s.scrumlr.odoo.model;

import java.util.List;

public class OdooResult {
    private String username;
    private Integer uid;
    private Integer company_id;
    private String session_id;
    private Integer res_id;
    private Object[] employee_id;
    private List timesheet_ids;
    private String date_from;
    private String date_to;
    private Integer product_id;
    private Integer general_account_id;
    private Integer product_uom_id;
    private Integer journal_id;
    private OdooValue value;
    private List<OdooRecord> records;

    public OdooValue getValue() {
        return value;
    }

    public void setValue(OdooValue value) {
        this.value = value;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getGeneral_account_id() {
        return general_account_id;
    }

    public void setGeneral_account_id(Integer general_account_id) {
        this.general_account_id = general_account_id;
    }

    public Integer getProduct_uom_id() {
        return product_uom_id;
    }

    public void setProduct_uom_id(Integer product_uom_id) {
        this.product_uom_id = product_uom_id;
    }

    public Integer getJournal_id() {
        return journal_id;
    }

    public void setJournal_id(Integer journal_id) {
        this.journal_id = journal_id;
    }

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

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public List<OdooRecord> getRecords() {
        return records;
    }

    public void setRecords(List<OdooRecord> records) {
        this.records = records;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }
}
