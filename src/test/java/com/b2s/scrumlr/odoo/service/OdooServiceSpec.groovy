package com.b2s.scrumlr.odoo.service

import com.b2s.scrumlr.odoo.model.OdooAccount
import com.b2s.scrumlr.odoo.model.TimesheetTask
import com.b2s.scrumlr.odoo.model.User
import com.b2s.scrumlr.odoo.utils.JsonUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

class OdooServiceSpec extends Specification{

    def OdooService odooService;

    void setup(){
        odooService = new OdooService();
    }

    void "test --happy path"(){
        when:
            odooService.getAccountId(_)
        then:
            thrown(Exception)
    }
}
