package com.b2s.scrumlr.odoo.service.impl;

import com.b2s.scrumlr.admin.dao.UserDaoImpl;
import com.b2s.scrumlr.admin.dao.VacationDateDaoImpl;
import com.b2s.scrumlr.admin.model.ScrumblrUser;
import com.b2s.scrumlr.admin.model.VacationDate;
import com.b2s.scrumlr.odoo.service.ConfigService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private VacationDateDaoImpl vacationDateDao;

    @Override
    public List<ScrumblrUser> getCustomsTasks() {
        return userDao.listScrumblrUserByDate(DateFormatUtils.format(new Date(),"yyyy-MM-dd"));
    }

    @Override
    public boolean isWorkDayToday() {
        final Calendar today=Calendar.getInstance();
        final String formatDate = String.valueOf(today.get(Calendar.YEAR));
        final List<VacationDate> dates = vacationDateDao.listVacationDates(formatDate);
        boolean flag = true;
        boolean isMatch = false;
        for(final VacationDate d: dates){
            if(d.getDate().equals(DateFormatUtils.format(today.getTime(),"yyyy-MM-dd"))){
                if(VacationDate.VACATION==d.getType()){
                    flag = false;
                }
                isMatch = true;
                break;
            }
        }
        if(!isMatch){
            if(today.getTime().getDay()==0 || today.getTime().getDay()==6){
                flag = false;
            }
        }

        return flag;
    }
}
