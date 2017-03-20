package com.b2s.scrumlr.odoo.config;

import com.b2s.scrumlr.odoo.service.TimesheetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleConfig.class);

    @Autowired
    private TimesheetService timesheetService;

    @Scheduled(cron = "0 0 16 * * MON-FRI")
    public void logTime() {
        LOGGER.info("======start log time task=========");
        timesheetService.logtime();
        LOGGER.info("======end log time task===========");
    }

    @Scheduled(cron = "0 40 16 * * FRI")
    public void submit() {
        LOGGER.info("======start submit task=========");
        timesheetService.submit();
        LOGGER.info("======end submit task===========");
    }
}
