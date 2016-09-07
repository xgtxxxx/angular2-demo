package com.b2s.scrumlr.odoo.service;

import com.b2s.scrumlr.admin.model.ScrumblrUser;

import java.util.List;

public interface ConfigService {
    public List<ScrumblrUser> getCustomsTasks();
    public boolean isWorkDayToday();
}
