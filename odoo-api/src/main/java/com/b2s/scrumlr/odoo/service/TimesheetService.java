package com.b2s.scrumlr.odoo.service;

import com.b2s.scrumlr.odoo.model.OdooLog;
import com.b2s.scrumlr.odoo.model.User;

import java.util.List;

public interface TimesheetService {
    public List<OdooLog> logtime(final List<User> users) throws Exception;
    public List<User> submitTimesheet(final List<User> users) throws Exception;
}
