package com.b2s.scrumlr.admin.utils;

import com.b2s.scrumlr.admin.model.CalendarDate;
import com.b2s.scrumlr.admin.model.VacationDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarFactory {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);

    public static synchronized SimpleDateFormat getFormat(){
        return FORMAT;
    }

    public static List<CalendarDate> createByMonth(final String year, final String month, final List<VacationDate> vacationDates) throws ParseException {
        final List<CalendarDate> dates = new ArrayList<>();
        final Date firstDate = getFirstDayOfMonth(year, month);
        dates.addAll(getOffset(firstDate));
        CalendarDate date = adaptTo(firstDate);
        dates.add(date);
        boolean hasNext = hasNext(date);
        while (hasNext){
            date = addNext(date);
            dates.add(date);
            hasNext = hasNext(date);
        }

        final List<VacationDate> filters = getCurrentMonthFilter(month, vacationDates);
        if(!filters.isEmpty()){
            for(final CalendarDate d : dates){
                for(final VacationDate filter: filters){
                    if(d.getType()!=0 && getFormat().format(d.getDate()).equals(filter.getDate())){
                        if(filter.getType()==VacationDate.VACATION){
                            d.setType(CalendarDate.VACATION);
                        }else{
                            d.setType(CalendarDate.SPECIAL_WORK_DAY);
                        }
                    }
                }
            }
        }
        return dates;
    }

    private static List<VacationDate> getCurrentMonthFilter(final String month, final List<VacationDate> vacationDates) throws ParseException {
        final List<VacationDate> list = new ArrayList<>();
        for(final VacationDate date : vacationDates){
            if(getFormat().parse(date.getDate()).getMonth()+1==Integer.parseInt(month)){
                list.add(date);
            }
        }
        return list;
    }

    private static CalendarDate addNext(final CalendarDate date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date.getDate());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        final Date next = calendar.getTime();
        return adaptTo(next);
    }

    private static boolean hasNext(final CalendarDate date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date.getDate());
        final int end = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if(end<=date.getDay()){
            return false;
        }else{
            return true;
        }
    }

    private static Date getFirstDayOfMonth(final String year, final String month) throws ParseException {
        String m = month;
        if(month.length()==1){
            m = '0'+m;
        }
        final String d = year+'-'+m+"-01";
        return getFormat().parse(d);
    }

    private static List<CalendarDate> getOffset(final Date date){
        final List<CalendarDate> offset = new ArrayList<>();
        final int day = date.getDay()==0?7:date.getDay();
        if(day>1){
            for(int i=0; i<day-1; i++){
                offset.add(CalendarDate.newEmptyInstance());
            }
        }
        return offset;
    }

    private static CalendarDate adaptTo(final Date date){
        final CalendarDate calendarDate = new CalendarDate();
        calendarDate.setDate(date);
        calendarDate.setYear(date.getYear());
        calendarDate.setMonth(date.getMonth()+1);
        calendarDate.setDay(date.getDate());
        calendarDate.setWeekDay(date.getDay());
        calendarDate.setCurrentDate(getFormat().format(new Date()).equals(getFormat().format(date)));
        calendarDate.setType(getDateType(date));
        return calendarDate;
    }

    private static int getDateType(final Date date){
        if(date.getDay()==0||date.getDay()==6){
            return CalendarDate.WEEKEND;
        }else{
            return CalendarDate.WORK_DAY;
        }
    }
}
