package com.b2s.scrumlr.admin.dao;

import com.b2s.scrumlr.admin.model.KeyValueParamter;
import com.b2s.scrumlr.admin.model.VacationDate;
import com.b2s.scrumlr.odoo.utils.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Repository
public class VacationDateDaoImpl extends BaseDao {
    private static final String TABLE_NAME = "vacationDate";

    @SuppressWarnings("unchecked")
    public List<VacationDate> listVacationDates(final String year) {
        final Object result = get(TABLE_NAME, year);
        List<VacationDate> dates = new ArrayList<>();
        if(result!=null){
            if(result instanceof String){
                dates = JsonUtil.fromJson((String)result, JsonUtil.getTypeFactory().constructCollectionType(List.class, VacationDate.class));
            }
        }
        Collections.sort(dates, new Comparator<VacationDate>() {
            @Override
            public int compare(final VacationDate o1, final VacationDate o2) {
                return o1.getDate().compareToIgnoreCase(o2.getDate());
            }
        });
        return dates;
    }

    public void saveVacationDate(final VacationDate date){
        final List<VacationDate> dates = this.listVacationDates(date.getYear());
        dates.add(date);
        save(new KeyValueParamter(date.getYear(),dates), TABLE_NAME);
    }

    public void deleteVacationDate(final VacationDate date) {
        final List<VacationDate> dates = this.listVacationDates(date.getYear());
        final List<VacationDate> newdates = new ArrayList<>();
        for(final VacationDate d: dates){
            if(date.getDate().equals(d.getDate())){
                continue;
            }
            newdates.add(d);
        }
        save(new KeyValueParamter(date.getYear(),newdates), TABLE_NAME);
    }
}
