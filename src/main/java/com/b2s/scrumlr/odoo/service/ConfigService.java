package com.b2s.scrumlr.odoo.service;

import com.b2s.scrumlr.admin.dao.UserDaoImpl;
import com.b2s.scrumlr.odoo.model.TimesheetTask;
import com.b2s.scrumlr.odoo.model.User;
import com.b2s.scrumlr.odoo.utils.JsonUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ConfigService {

    @Autowired
    private UserDaoImpl userDao;

    public List<User> getUsers(){
        final List<User> users = userDao.getUsers(User.class);
        final String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        initDateForUsers(users, date);

        return users;
    }

    public List<User> getUsers(final String date){
        final List<User> users = userDao.getUsers(User.class);
        initDateForUsers(users, date);

        return users;
    }

    private void initDateForUsers(final List<User> users, final String date){
        for(final User user : users){
            if(Objects.isNull(user.getDate())){
                user.setDate(date);
            }
            for(final TimesheetTask task : user.getTimesheetTasks()){
                task.setCurrentDate(user.getDate());
            }
        }
    }

}
