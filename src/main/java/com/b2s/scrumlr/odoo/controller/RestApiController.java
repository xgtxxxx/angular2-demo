package com.b2s.scrumlr.odoo.controller;

import com.b2s.scrumlr.admin.model.Project;
import com.b2s.scrumlr.admin.model.ScrumblrUser;
import com.b2s.scrumlr.odoo.model.OdooLog;
import com.b2s.scrumlr.odoo.model.ResponseMap;
import com.b2s.scrumlr.odoo.model.TimesheetTask;
import com.b2s.scrumlr.odoo.model.User;
import com.b2s.scrumlr.odoo.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/timesheet")
public class RestApiController {
//    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiController.class);
//    @Autowired
//    private TimesheetService timesheetService;
//
//    @Autowired
//    private ConfigService configService;
//
//    @Autowired
//    private LogsService logsService;
//
//    @RequestMapping("/log")
//    @ResponseBody
//    public ResponseMap logTime(@RequestBody final List<User> users) throws Exception {
//        LOGGER.info("before config : " + JsonUtil.toJson(users));
//        try {
//            /*if(!this.configService.isWorkDayToday()){
//                LOGGER.info("Today is not work day!");
//                return new ResponseMap(false);
//            }*/
//            //----------------------started by Allen Wu 2016/10/26----------------------
//            //check whether user's date is vacation or not,if is, set TimesheetTasks
//            if (!users.isEmpty() && !StringUtils.isEmpty(users.get(0).getDate())) {
//                if (this.configService.isVacationDate(users.get(0).getDate())) {
//                    //if today is vacation day, log time to 'holiday'.
//                    for (final User user : users) {
//                        user.getTimesheetTasks().clear();
//                        user.addTimesheetTasks(createVacationTask(user.getDate()));
//                    }
//                }
//            } else {
//                LOGGER.info("date of  user is null");
//                return new ResponseMap(false);
//            }
//
//            //----------------------end by Allen Wu 2016/10/26--------------------------
//            final List<ScrumblrUser> customsTasks = this.configService.getCustomsTasks();
//            processCustom(users, customsTasks);
//            LOGGER.info("after config : " + JsonUtil.toJson(users));
//            final List<OdooLog> logs = this.timesheetService.logtime(users);
//            final String result = JsonUtil.toJson(logs);
//            LOGGER.info(result);
//            this.configService.saveCustomsTasks(customsTasks);
//            //----------------------started by Allen Wu 2016/10/27----------------------
//            logsService.saveLogs(users.get(0).getDate(), logs);
//            //----------------------end by Allen Wu 2016/10/27--------------------------
//            return new ResponseMap(true, result);
//        } catch (final Exception e) {
//            LOGGER.error(e.getMessage(), e);
//            return new ResponseMap(false);
//        }
//    }
//
//    private TimesheetTask createVacationTask(final String date){
//        final TimesheetTask timesheetTask = new TimesheetTask();
//        timesheetTask.setProject("Projects / Internal");
//        timesheetTask.setHours(8);
//        timesheetTask.setTask("Holiday");
//        timesheetTask.setCurrentDate(date);
//        return timesheetTask;
//    }
//
//    private void processCustom(final List<User> users, final List<ScrumblrUser> customsTasks) {
//        final Map<String, ScrumblrUser> map = new HashMap<>();
//        for (final ScrumblrUser task : customsTasks) {
//            if (task.getCustoms().isActive()) {
//                map.put(task.getAccount(), task);
//            }
//        }
//        for (final User user : users) {
//            final ScrumblrUser task = map.get(user.getAccount().getLogin());
//            // if user is not loged in scrumblr, jira or v1, and custom task exist, use the custom task to log odoo
//            if (task != null && !user.isLogged()) {
//                final List<TimesheetTask> tasks = new ArrayList<>();
//                final List<Project> projects = task.getCustoms().getProjects();
//                for (final Project p : projects) {
//                    final TimesheetTask tt = new TimesheetTask();
//                    tt.setProject(p.getProject());
//                    tt.setTask(p.getTask());
//                    tt.setHours(p.getHours());
//                    tt.setCurrentDate(user.getDate());
//                    tasks.add(tt);
//                }
//                user.setTimesheetTasks(tasks);
//            }
//        }
//    }
//
//    @RequestMapping("/submit")
//    @ResponseBody
//    public ResponseMap submit(@RequestBody final List<User> users) throws Exception {
//        LOGGER.info(JsonUtil.toJson(users));
//        try {
//            final List<User> logs = this.timesheetService.submitTimesheet(users);
//            final String result = JsonUtil.toJson(logs);
//            LOGGER.info(result);
//            return new ResponseMap(true, result);
//        } catch (final Exception e) {
//            LOGGER.error(e.getMessage(), e);
//            return new ResponseMap(false);
//        }
//    }
}
