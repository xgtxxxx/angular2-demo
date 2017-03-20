package com.b2s.scrumlr.odoo.model;

public class OdooRecord {
    private double total_difference;
    private String date_from;
    private String date_to;
    private int id;

    public double getTotal_difference() {
        return total_difference;
    }

    public void setTotal_difference(double total_difference) {
        this.total_difference = total_difference;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
