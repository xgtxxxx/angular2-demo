package com.b2s.scrumlr.admin.dao

import com.b2s.scrumlr.admin.model.ScrumblrUser
import com.b2s.scrumlr.odoo.utils.JsonUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(locations = ['classpath:spring-application-admin-test.xml'])
class UserDaoSpec extends Specification{
    @Autowired
    UserDaoImpl userDao;

    void "testSave"(){
        given:
        def user = new ScrumblrUser(
                name: "Gavin",
                account: "gxi",
                password: "rSP3=HEZ",
                defaultTask: "Build",
                customs: null
        )
        when:
        userDao.saveScrumblrUser(user);
        then:
        notThrown(Exception)
    }

    void "testListAll"(){
        given:
        def tableName = "userOdooMap"
        when:
        Map map = (Map)userDao.query(tableName);
        Collection ss = map.values();
        for(String s : ss){
            println(JsonUtil.fromJson(s,ScrumblrUser))
        }
        then:
        notThrown(Exception)
    }

    void "testDelete"(){
        given:
        def key = "Gavin";
        when:
        userDao.delete(key);
        then:
        notThrown(Exception)
    }
}
