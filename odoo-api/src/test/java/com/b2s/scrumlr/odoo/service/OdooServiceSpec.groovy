package com.b2s.scrumlr.odoo.service

import com.b2s.scrumlr.odoo.model.OdooAccount
import com.b2s.scrumlr.odoo.model.TimesheetTask
import com.b2s.scrumlr.odoo.model.User
import com.b2s.scrumlr.odoo.utils.JsonUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import spock.lang.Subject

@ContextConfiguration(locations = ['classpath:spring-application-config-test.xml'])
class OdooServiceSpec extends Specification{

    @Autowired
    def OdooService odooService;

    void "getSessionId() --happy path"(){
        given:
            def User user = new User(
                name : 'Gavin',
                account: new OdooAccount(
                        "login":"gxi",
                        "password":"rSP3=HEZ"
                )
            )
        when:
            String sessionId = odooService.getSessionId(user);
        then:
            notThrown(Exception)
    }

    void "getUserId() --happy path"(){
        given:
        def User user = new User(
                name : 'Gavin',
                sessionId: "1ae39005d0372e2267f5aff415c7ef53e15fa572"
        )
        when:
        def u = odooService.getUserId(user);
        then:
        notThrown(Exception)
        print(u.getUserId())
    }

    void "getResId()"(){
        given:
        def User user = new User(
                name : 'Gavin',
                userId: 558,
                sessionId: "26cab010f1d341f74fdc7558b5897d134367d69a"
        )
        when:
        odooService.getCurrentTimeSheetResId(user);
        then:
        notThrown(Exception)
        print(user.getResId())
    }

    void "getContextDetail()"(){
        given:
        def User user = new User(
                name : 'Gavin',
                userId: 558,
                resId: 17514,
                sessionId: "c62f09517a38a7fd7212a8b9519b94feb41c1d4c"
        )
        when:
        odooService.getContextDetail(user);
        then:
        notThrown(Exception)
        print(JsonUtil.toJson(user))
    }

    void "getTimeSheetContext()"(){
        given:
        def User user = new User(
                name : 'Gavin',
                userId: 558,
                resId: 17514,
                sessionId: "ff33ead9c2d71fdff37892c89db727fd8cb40f4a"
        )
        when:
        odooService.getTimeSheetContext(user);
        then:
        notThrown(Exception)
        print(JsonUtil.toJson(user))
    }

    void "getProjects()"(){
        given:
        def User user = new User(
                name : 'Gavin',
                userId: 558,
                resId: 17514,
                sessionId: "ff33ead9c2d71fdff37892c89db727fd8cb40f4a",
                timesheetTasks: [
                        new TimesheetTask(
                                project: "Core - Web Production Support"
                        ),
                        new TimesheetTask(
                                project: "Core - Symbio Projects"
                        )
                ]
        )
        when:
        odooService.getProjects(user);
        then:
        notThrown(Exception)
        print(JsonUtil.toJson(user))
    }

    void "getTasksByProject()"(){
        given:
        def User user = new User(
                name : 'Gavin',
                userId: 558,
                resId: 17514,
                sessionId: "ff33ead9c2d71fdff37892c89db727fd8cb40f4a",
                timesheetTasks: [
                        new TimesheetTask(
                                project: "Core - Web Production Support",
                                projectId: 272,
                                task: "build"
                        ),
                        new TimesheetTask(
                                project: "Core - Symbio Projects",
                                projectId: 1055,
                                task: "build"
                        )
                ]
        )
        when:
        odooService.getTasksByProject(user);
        then:
        notThrown(Exception)
        print(JsonUtil.toJson(user))
    }

    void "getStageId()"(){
        given:
        def User user = new User(
                name : 'Gavin',
                userId: 558,
                resId: 17514,
                sessionId: "ff33ead9c2d71fdff37892c89db727fd8cb40f4a",
                timesheetTasks: [
                        new TimesheetTask(
                                project: "Core - Web Production Support",
                                projectId: 272,
                                task: "build",
                                taskId: 446
                        ),
                        new TimesheetTask(
                                project: "Core - Symbio Projects",
                                projectId: 1055,
                                task: "build",
                                taskId: 1333
                        )
                ]
        )
        when:
        odooService.getStageId(user);
        then:
        notThrown(Exception)
        print(JsonUtil.toJson(user))
    }

    void "getAccountId()"() {
        given:
        def User user = new User(
                name : 'Gavin',
                userId: 558,
                resId: 17514,
                sessionId: "ff33ead9c2d71fdff37892c89db727fd8cb40f4a",
                timesheetTasks: [
                        new TimesheetTask(
                                project: "Core - Web Production Support",
                                projectId: 272,
                                task: "build",
                                taskId: 446,
                                stageId: 38
                        ),
                        new TimesheetTask(
                                project: "Core - Symbio Projects",
                                projectId: 1055,
                                task: "build",
                                taskId: 1333,
                                stageId: 16
                        )
                ]
        )
        when:
        odooService.getAccountId(user);
        then:
        notThrown(Exception)
        print(JsonUtil.toJson(user))
    }


    void "addTimeToCurrentTimeSheet()"(){
        given:
        def User user = new User(
                name : 'Gavin',
                userId: 558,
                resId: 17514,
                sessionId: "ff33ead9c2d71fdff37892c89db727fd8cb40f4a",
                journalId : 3,
                generalAccountId: 62,
                productUomId: 5,
                productId: 506,
                timesheetTasks: [
                        new TimesheetTask(
                                project: "Core - Web Production Support",
                                projectId: 272,
                                task: "build",
                                taskId: 446,
                                stageId: 38,
                                hours: 5,
                                currentDate: "2016-07-27",
                                accountId:273
                        ),
                        new TimesheetTask(
                                project: "Core - Symbio Projects",
                                projectId: 1055,
                                task: "build",
                                taskId: 1333,
                                stageId: 16,
                                hours: 3,
                                currentDate: "2016-07-27",
                                accountId:1057
                        )
                ]
        )
        when:
        odooService.addTimeToCurrentTimeSheet(user);
        then:
        notThrown(Exception)
    }

    void "createAndLogTimeSheet()"(){
        given:
        def User user = new User(
                name : 'Gavin',
                userId: 558,
                //resId: 17514,
                sessionId: "ff33ead9c2d71fdff37892c89db727fd8cb40f4a",
                journalId : 3,
                generalAccountId: 62,
                productUomId: 5,
                productId: 506,
                timesheetTasks: [
                        new TimesheetTask(
                                project: "Core - Web Production Support",
                                projectId: 272,
                                task: "build",
                                taskId: 446,
                                stageId: 38,
                                hours: 5,
                                currentDate: "2016-07-27",
                                accountId:273
                        ),
                        new TimesheetTask(
                                project: "Core - Symbio Projects",
                                projectId: 1055,
                                task: "build",
                                taskId: 1333,
                                stageId: 16,
                                hours: 3,
                                currentDate: "2016-07-27",
                                accountId:1057
                        )
                ]
        )
        when:
        odooService.createAndLogTimeSheet(user);
        then:
        notThrown(Exception)
    }

    void "getCurrentTimeSheetDetail()"(){
        given:
        def User user = new User(
                name : 'Gavin',
                userId: 558,
                resId: 17514,
                sessionId: "c62f09517a38a7fd7212a8b9519b94feb41c1d4c",
                journalId : 3,
                generalAccountId: 62,
                productUomId: 5,
                productId: 506,
                employeeId: 550,
                timesheetIds: [164781,164780],
                dateFrom: "2016-07-25",
                dateTo: "2016-07-31"
        )
        when:
        odooService.getCurrentTimeSheetDetail(user);
        then:
        notThrown(Exception)
    }

    void "submitToManager()"(){
        given:
        def User user = new User(
                name : 'Gavin',
                userId: 558,
                resId: 17852,
                sessionId: "af78493cd62173ee34570d9a4d85d3c45d44d6c4"
        )
        when:
        odooService.submitToManager(user);
        then:
        notThrown(Exception)
    }

    void "getSubmitStatus()"(){
        given:
        def User user = new User(
                name : 'Gavin',
                userId: 558,
                resId: 17847,
                sessionId: "26cab010f1d341f74fdc7558b5897d134367d69a"
        )
        when:
        odooService.getSubmitStatus(user);
        then:
        notThrown(Exception)
        print(JsonUtil.toJson(user))
    }
}
