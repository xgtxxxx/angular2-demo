package com.b2s.scrumlr.admin.model;

import java.util.Date;

public class CalendarDate {
    public static final int WORK_DAY = 1;
    public static final int SPECIAL_WORK_DAY = 2;
    public static final int WEEKEND = 3;
    public static final int VACATION = 4;
    public static final int EMPTY = 0;
    private int year;
    private int month;
    private int day;
    private Date date;
    private int weekDay;
    private int type;
    private boolean isCurrentDate;

    public static CalendarDate newEmptyInstance(){
        final CalendarDate d= new CalendarDate();
        d.setType(EMPTY);
        return d;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isCurrentDate() {
        return isCurrentDate;
    }

    public void setCurrentDate(boolean currentDate) {
        isCurrentDate = currentDate;
    }
}
