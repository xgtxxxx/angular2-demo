package com.b2s.scrumlr.odoo.service;

import com.b2s.scrumlr.odoo.model.User;

public interface OdooService {
    public User getSessionId(final User user) throws Exception;

    public User getUserId(final User user) throws Exception;

    public User getCurrentTimeSheetResId(final User user) throws Exception;

    public User getContextDetail(final User user) throws Exception;

    public User getContextDetail(final User user, final boolean force) throws Exception;

    public User getTimeSheetContext(final User user) throws Exception;

    public User getProjects(final User user) throws Exception;

    public User getTasksByProject(final User user) throws Exception;

    public User getStageId(final User user) throws Exception;

    public User getAccountId(final User user) throws Exception;

    public User addTimeToCurrentTimeSheet(final User user) throws Exception;

    public User createAndLogTimeSheet(final User user) throws Exception;

    public User submitToManager(final User user) throws Exception;

    public User getCurrentTimeSheetDetail(final User user) throws Exception;

    public User getCurrentTimeSheetDetail(final User user, final boolean isFull) throws Exception;

    public User getSubmitStatus(final User user) throws Exception;
}
