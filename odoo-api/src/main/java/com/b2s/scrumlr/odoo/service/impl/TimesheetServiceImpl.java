package com.b2s.scrumlr.odoo.service.impl;

import com.b2s.scrumlr.odoo.model.OdooLog;
import com.b2s.scrumlr.odoo.model.User;
import com.b2s.scrumlr.odoo.service.OdooService;
import com.b2s.scrumlr.odoo.service.TimesheetService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TimesheetServiceImpl implements TimesheetService {
    private final Logger LOG = LoggerFactory.getLogger(TimesheetServiceImpl.class);

    @Autowired
    private OdooService odooService;

    private List<LogTimesheetTask> tasks;

    private List<SubmitTimesheetTask> submitTasks;

    private void checkDate(final User user){
        if(StringUtils.isEmpty(user.getDate())){
            user.setDate(DateFormatUtils.format(new Date(),"yyyy-MM-dd"));
        }
    }

    @Override
    public List<OdooLog> logtime(final List<User> users) throws Exception {
        tasks = new CopyOnWriteArrayList<LogTimesheetTask>();
        for(final User user : users){
            checkDate(user);
            tasks.add(new LogTimesheetTask(user));
        }
        int count = 5;
        do{
            if(count<=0){
                final StringBuffer sb = new StringBuffer();
                for(final LogTimesheetTask task: tasks){
                    sb.append(task.getUser()).append(' ');
                }
                LOG.warn("Can not log time for user {}.", sb);
                break;
            }
            execute();
            count--;
        }while (!tasks.isEmpty());

        return getLogs(users);
    }

    private List<OdooLog> getLogs(final List<User> users){
        final List<OdooLog> logs = new ArrayList<>();
        for(final User user: users){
            if(user.getLogs()==null){
                logs.add(new OdooLog(user.getName(),true,false));
            }else{
                logs.add(user.getLogs());
            }
        }
        return logs;
    }

    private void execute() throws Exception{
        final ExecutorService executorService = Executors.newFixedThreadPool(1);
        for(final LogTimesheetTask task : tasks){
            executorService.execute(task);
        }
        tasks.clear();
        executorService.shutdown();
        while(!executorService.isTerminated()){
            Thread.sleep(200);
        }
    }

    private void doSubmit() throws Exception{
        final ExecutorService executorService = Executors.newFixedThreadPool(1);
        for(final SubmitTimesheetTask task : submitTasks){
            executorService.execute(task);
        }
        submitTasks.clear();
        executorService.shutdown();
        while(!executorService.isTerminated()){
            Thread.sleep(200);
        }
    }

    @Override
    public List<User> submitTimesheet(final List<User> users) throws Exception {
        submitTasks = new CopyOnWriteArrayList<SubmitTimesheetTask>();
        for(final User user : users){
            checkDate(user);
            submitTasks.add(new SubmitTimesheetTask(user));
        }
        int count = 5;
        do{
            if(count<=0){
                final StringBuffer sb = new StringBuffer();
                for(final SubmitTimesheetTask task: submitTasks){
                    sb.append(task.getUser()).append(' ');
                }
                LOG.warn("Can not submit timesheet for user {}.", sb);
                break;
            }
            doSubmit();
            count--;
        }while (!submitTasks.isEmpty());

        return users;
    }

    class SubmitTimesheetTask implements Runnable{

        private User user;

        SubmitTimesheetTask(final User user){
            this.user = user;
        }

        public String getUser(){
            return user.getName();
        }

        @Override
        public void run() {
            try {
                synchronized (odooService){
                    odooService.getSessionId(user);
                    odooService.getUserId(user);
                    odooService.getCurrentTimeSheetResId(user);
                    odooService.getContextDetail(user);
                    odooService.getCurrentTimeSheetDetail(user,true);
                    odooService.submitToManager(user);
                }
            } catch (final Exception e) {
                LOG.error("log timesheet for {} failed.", user.getName(), e);
                //restart this task here
                submitTasks.add(new SubmitTimesheetTask(user));
            }
        }
    }

    class LogTimesheetTask implements Runnable {

        private User user;

        LogTimesheetTask(final User user){
            this.user = user;
        }

        public String getUser(){
            return user.getName();
        }

        @Override
        public void run() {
            try {
                synchronized (odooService){
                    odooService.getSessionId(user);
                    odooService.getUserId(user);
                    odooService.getCurrentTimeSheetResId(user);
                    odooService.getContextDetail(user);
                    odooService.getTimeSheetContext(user);
                    odooService.getProjects(user);
                    odooService.getTasksByProject(user);
                    odooService.getStageId(user);
                    odooService.getAccountId(user);
                    if(user.isShouldCreate()){
                        odooService.createAndLogTimeSheet(user);
                    }else{
                        odooService.addTimeToCurrentTimeSheet(user);
                    }
                    odooService.getCurrentTimeSheetResId(user);
                    odooService.getContextDetail(user, true);
                    odooService.getCurrentTimeSheetDetail(user);
                }
            } catch (final Exception e) {
                LOG.error("log timesheet for {} failed.", user.getName(), e);
                //restart this task here
                tasks.add(new LogTimesheetTask(user));
            }
        }
    }
}
