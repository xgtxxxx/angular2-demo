package com.b2s.scrumlr.odoo.service

import com.b2s.scrumlr.odoo.model.OdooAccount
import com.b2s.scrumlr.odoo.model.TimesheetTask
import com.b2s.scrumlr.odoo.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(locations = ['classpath:spring-application-config-test.xml'])
class TimesheetServiceSpec extends Specification {

    @Autowired
    def TimesheetService timesheetService;

    void "logTime()"(){
        given:
        def users = [
                new User(
                        name: "Gavin",
                        account: new OdooAccount(
                                login: "gxi",
                                password: "gxi"
                        ),
                        timesheetTasks: [
                                new TimesheetTask(
                                        project: "test project",
                                        task: "build",
                                        currentDate: "2016-07-26",
                                        hours: 8
                                )
                        ]
                ),
                new User(
                        name: "Clark",
                        account: new OdooAccount(
                                login: "cchen",
                                password: "cchen"
                        ),
                        timesheetTasks: [
                                new TimesheetTask(
                                        project: "test project",
                                        task: "build",
                                        currentDate: "2016-07-26",
                                        hours: 4
                                ),
                                new TimesheetTask(
                                        project: "test test",
                                        task: "build",
                                        currentDate: "2016-07-26",
                                        hours: 4
                                )
                        ]
                ),
                new User(
                        name: "Felix",
                        account: new OdooAccount(
                                login: "fchen",
                                password: "fchen"
                        ),
                        timesheetTasks: [
                                new TimesheetTask(
                                        project: "test project",
                                        task: "build",
                                        currentDate: "2016-07-26",
                                        hours: 8
                                )
                        ]
                )
        ]
        when:
        timesheetService.logtime(users);
        then:
        notThrown(Exception)
    }
}
