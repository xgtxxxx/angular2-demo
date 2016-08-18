package com.b2s.scrumlr.admin.model;

public class VacationDate {
    public static final int VACATION = 1;
    public static final int WORK_DAY = 2;
    private String year;
    private String date;
    private int type;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
