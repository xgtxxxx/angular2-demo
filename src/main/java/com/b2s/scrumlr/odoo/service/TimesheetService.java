package com.b2s.scrumlr.odoo.service;

import com.b2s.scrumlr.admin.model.AdminLogTask;
import com.b2s.scrumlr.odoo.model.EmailEvent;
import com.b2s.scrumlr.odoo.model.EmailType;
import com.b2s.scrumlr.odoo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimesheetService {
    private final Logger LOG = LoggerFactory.getLogger(TimesheetService.class);

    @Autowired
    private OdooService odooService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * Admin log
     * @param task
     */
    public void logtime(final AdminLogTask task) {
        final List<User> users = configService.getUsers(task.getDate());
        final List<User> selectedUsers =
            users
                .stream()
                .filter(user -> isSelected(task.getUserIds(), user.getId()))
                .collect(Collectors.toList());
        doLogtime(selectedUsers);
    }

    private boolean isSelected(final List<String> ids, final String id) {
        return ids
            .stream()
            .filter(selectedId -> selectedId.equals(id))
            .findAny()
            .isPresent();
    }

    /**
     * auto log job
     */
    public void logtime() {
        final List<User> users = configService.getUsers();
        doLogtime(users);
    }

    private void doLogtime(final List<User> users){
        for (final User user : users) {
            this.doLogtime(user);
        }
        publishEmailEvent(users, EmailType.LOGTIME);
    }

    public void submit() {
        final List<User> users = configService.getUsers();
        for (final User user : users) {
            this.doSubmit(user);
        }
        publishEmailEvent(users, EmailType.SUBMIT);
    }

    private void publishEmailEvent(final List<User> users, final EmailType emailType) {
        final EmailEvent emailEvent =
            EmailEvent
                .builder()
                .withEmailType(emailType)
                .withUsers(users)
                .build();
        applicationEventPublisher.publishEvent(emailEvent);
    }

    private void doSubmit(final User user) {
        try {
            odooService.getSessionId(user);
            odooService.getUserId(user);
            odooService.getCurrentTimeSheetResId(user);
            odooService.getContextDetail(user);
            odooService.getCurrentTimeSheetDetail(user, true);
            odooService.submitToManager(user);
        } catch (final Exception e) {
            user.setSuccess(false);
            LOG.error("log timesheet for {} failed.", user.getName(), e);
        }
    }

    private void doLogtime(final User user) {
        try {
            odooService.getSessionId(user);
            odooService.getUserId(user);
            odooService.getCurrentTimeSheetResId(user);
            odooService.getContextDetail(user);
            odooService.getTimeSheetContext(user);
            odooService.getProjects(user);
            odooService.getTasksByProject(user);
            odooService.getStageId(user);
            odooService.getAccountId(user);
            if (user.isShouldCreate()) {
                odooService.createAndLogTimeSheet(user);
            } else {
                odooService.addTimeToCurrentTimeSheet(user);
            }
            odooService.getCurrentTimeSheetResId(user);
            odooService.getContextDetail(user, true);
            odooService.getCurrentTimeSheetDetail(user);
        } catch (final Exception e) {
            user.setSuccess(false);
            LOG.error("log timesheet for {} failed.", user.getName(), e);
        }
    }

}
