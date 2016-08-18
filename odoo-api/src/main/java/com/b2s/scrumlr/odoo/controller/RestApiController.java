package com.b2s.scrumlr.odoo.controller;

import com.b2s.scrumlr.odoo.model.OdooLog;
import com.b2s.scrumlr.odoo.model.ResponseMap;
import com.b2s.scrumlr.odoo.model.User;
import com.b2s.scrumlr.odoo.service.TimesheetService;
import com.b2s.scrumlr.odoo.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/timesheet")
public class RestApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiController.class);
    @Autowired
    private TimesheetService timesheetService;

    @RequestMapping("/log")
    @ResponseBody
    public ResponseMap logTime(@RequestBody List<User> users) throws Exception{
        LOGGER.info(JsonUtil.toJson(users));
        try{
            final List<OdooLog> logs = this.timesheetService.logtime(users);
            final String result = JsonUtil.toJson(logs);
            LOGGER.info(result);
            return new ResponseMap(true,result);
        }catch (final Exception e){
            LOGGER.error(e.getMessage(),e);
            return new ResponseMap(false);
        }

    }

    @RequestMapping("/submit")
    @ResponseBody
    public ResponseMap submit(@RequestBody List<User> users) throws Exception{
        LOGGER.info(JsonUtil.toJson(users));
        try{
            final List<User> logs = this.timesheetService.submitTimesheet(users);
            final String result = JsonUtil.toJson(logs);
            LOGGER.info(result);
            return new ResponseMap(true,result);
        }catch (final Exception e){
            LOGGER.error(e.getMessage(),e);
            return new ResponseMap(false);
        }
    }
}
