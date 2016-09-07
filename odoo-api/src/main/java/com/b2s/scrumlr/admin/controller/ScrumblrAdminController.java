package com.b2s.scrumlr.admin.controller;

import com.b2s.scrumlr.admin.dao.EpicLinkDaoImpl;
import com.b2s.scrumlr.admin.dao.UserDaoImpl;
import com.b2s.scrumlr.admin.dao.VacationDateDaoImpl;
import com.b2s.scrumlr.admin.model.CalendarDate;
import com.b2s.scrumlr.admin.model.Constants;
import com.b2s.scrumlr.admin.model.EpicLinkMap;
import com.b2s.scrumlr.admin.model.KeyValueParamter;
import com.b2s.scrumlr.admin.model.ScrumblrAccount;
import com.b2s.scrumlr.admin.model.ScrumblrUser;
import com.b2s.scrumlr.admin.model.VacationDate;
import com.b2s.scrumlr.admin.utils.CalendarFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class ScrumblrAdminController {
    private static final Logger LOG = LoggerFactory.getLogger(ScrumblrAdminController.class);

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private EpicLinkDaoImpl epicLinkDao;

    @Autowired
    private VacationDateDaoImpl vacationDateDao;

    @ResponseBody
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestBody final ScrumblrAccount account, final HttpSession session){
        final Map<String,Object> result = new HashMap<>();
        if(userDao.isAdmin(account.getUname(), account.getPwd())){
            session.setAttribute(Constants.USER, account.getUname());
            result.put("success", true);
            result.put("token",session.getId());
        }else{
            result.put("success", false);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/user/list")
    public List<ScrumblrUser> listScrumblrUser(){
        return userDao.listScrumblrUser();
    }

    @ResponseBody
    @RequestMapping("/user/save")
    public KeyValueParamter saveScrumblrUser(@RequestBody final ScrumblrUser user){
        this.userDao.saveScrumblrUser(user);
        return new KeyValueParamter("success","true");
    }

    @ResponseBody
    @RequestMapping("/epicLink/list")
    public List<EpicLinkMap> listEpicLinks(){
        return epicLinkDao.listEpicLinks();
    }

    @ResponseBody
    @RequestMapping("/epicLink/save")
    public KeyValueParamter saveEpicLink(@RequestBody final EpicLinkMap map){
        this.epicLinkDao.saveEpicLink(map);
        return new KeyValueParamter("success","true");
    }

    @ResponseBody
    @RequestMapping(value="/vacationDate/list/{year}", method = RequestMethod.GET)
    public List<VacationDate> listVacationDates(@PathVariable final String year){
        return vacationDateDao.listVacationDates(year);
    }

    @ResponseBody
    @RequestMapping("/vacationDate/delete")
    public KeyValueParamter deleteVacationDate(@RequestBody final VacationDate date){
        this.vacationDateDao.deleteVacationDate(date);
        return new KeyValueParamter("success","true");
    }

    @ResponseBody
    @RequestMapping("/vacationDate/save")
    public KeyValueParamter saveVacationDate(@RequestBody final VacationDate date){
        date.setYear(date.getDate().substring(0,4));
        this.vacationDateDao.saveVacationDate(date);
        return new KeyValueParamter("success","true");
    }

    @ResponseBody
    @RequestMapping(value="/calendar/list/{year}/{month}", method = RequestMethod.GET)
    public List<List<CalendarDate>> listCalendarDates(@PathVariable final String year, @PathVariable final String month){
        try{
            final List<VacationDate> vacationDates = vacationDateDao.listVacationDates(year);
            final List<CalendarDate> dates = CalendarFactory.createByMonth(year, month, vacationDates);
            final List<List<CalendarDate>> weeks = new ArrayList<>();
            List<CalendarDate> week = new ArrayList<>();
            for(int i=0; i<dates.size(); i++){
                if(i%7==0){
                    weeks.add(week);
                    week = new ArrayList<>();
                }
                week.add(dates.get(i));
            }
            if(week.size()<7){
                weeks.add(week);
            }
            return weeks;
        } catch (final ParseException e) {
            LOG.error(e.getMessage(), e);
        }
        return new ArrayList<>();
    }

    @ResponseBody
    @RequestMapping(value="/calendar/tasks/{date}", method = RequestMethod.GET)
    public List<ScrumblrUser> listTasksByDate(@PathVariable final String date){
        return userDao.listScrumblrUserByDate(date);
    }

    @ResponseBody
    @RequestMapping(value="/calendar/tasks/save/{date}", method = RequestMethod.POST)
    public KeyValueParamter saveTasksByDate(@PathVariable final String date, @RequestBody final List<ScrumblrUser> users){
        this.userDao.saveScrumblrUserByDate(date, users);
        return new KeyValueParamter("success","true");
    }

    @ResponseBody
    @RequestMapping(value="/scrumblr/users", method = RequestMethod.GET)
    public List<ScrumblrAccount> listScrumblrUsers(){
        return userDao.listScrumblrAccount();
    }

    @ResponseBody
    @RequestMapping(value="/scrumblr/user/save", method = RequestMethod.POST)
    public KeyValueParamter saveScrumblrUser(@RequestBody final ScrumblrAccount account){
        this.userDao.saveScrumblrAccount(account);
        return new KeyValueParamter("success","true");
    }

    @ResponseBody
    @RequestMapping(value="/scrumblr/user/delete", method = RequestMethod.POST)
    public KeyValueParamter delScrumblrUser(@RequestBody final ScrumblrAccount account){
        this.userDao.deleteScrumblrAccount(account);
        return new KeyValueParamter("success","true");
    }
}
