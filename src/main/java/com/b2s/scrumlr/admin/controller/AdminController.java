package com.b2s.scrumlr.admin.controller;

import com.b2s.scrumlr.admin.dao.UserDaoImpl;
import com.b2s.scrumlr.admin.model.AdminLogTask;
import com.b2s.scrumlr.admin.model.AdminUser;
import com.b2s.scrumlr.admin.model.BaseUser;
import com.b2s.scrumlr.admin.model.Constants;
import com.b2s.scrumlr.odoo.service.TimesheetService;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private TimesheetService timesheetService;

    @RequestMapping(value = "/current-user")
    @JsonView(AdminUser.WithoutPasswordView.class)
    public BaseUser getLoggedUser(final HttpSession session) {
        final BaseUser baseUser = (BaseUser) session.getAttribute(Constants.USER);

        if(Objects.nonNull(baseUser)){
            return baseUser;
        }

        throw new RuntimeException("User has not login!");
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @JsonView(AdminUser.WithoutPasswordView.class)
    public List<AdminUser> getUsers() {
        final List<AdminUser> users = userDao.getUsers(AdminUser.class);

        return users;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void saveUser(@RequestBody final AdminUser user) {
        userDao.saveUser(user);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    @JsonView(AdminUser.WithoutPasswordView.class)
    public AdminUser getUser(@PathVariable final String id, final HttpSession session) {
        final BaseUser baseUser = (BaseUser) session.getAttribute(Constants.USER);
        final AdminUser adminUser = userDao.getUser(id);

        if (!"all".equals(baseUser.getAuthority()) && !baseUser.getAuthority().equals(adminUser.getName())) {
            throw new RuntimeException("you are not be super user!");
        }
        return adminUser;
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deletedUser(@PathVariable final String id) {
        userDao.deleteUser(id);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseUser login(@RequestBody final BaseUser user, final HttpSession session) {

        final List<BaseUser> users = userDao.getUsers(BaseUser.class);
        final Optional<BaseUser> loginUser =
            users
                .stream()
                .filter(u ->
                    u.getAccount().getLogin().equalsIgnoreCase(user.getAccount().getLogin())
                        && u.getAccount().getPassword().equalsIgnoreCase(user.getAccount().getPassword()))
                .findAny();

        final BaseUser currentUser = addAuthority(loginUser.get());
        session.setAttribute(Constants.USER, currentUser);

        return currentUser;
    }

    @RequestMapping(value = "/log-time", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> logTime(@RequestBody final AdminLogTask task) {
        this.timesheetService.logtime(task);
        final Map<String, String> result = new HashMap<>();
        result.put("success", "true");
        return result;
    }

    private BaseUser addAuthority(final BaseUser user) {
        final BaseUser baseUser = new BaseUser();
        baseUser.setAuthority("Gavin".equalsIgnoreCase(user.getName()) || "Felix".equalsIgnoreCase(user.getName()) ? "all" : user.getName());
        baseUser.setName(user.getName());
        baseUser.setId(user.getId());

        return baseUser;
    }
}
