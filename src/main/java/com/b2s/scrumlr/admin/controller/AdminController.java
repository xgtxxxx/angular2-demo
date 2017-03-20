package com.b2s.scrumlr.admin.controller;

import com.b2s.scrumlr.admin.dao.UserDaoImpl;
import com.b2s.scrumlr.admin.model.BaseUser;
import com.b2s.scrumlr.admin.model.Constants;
import com.b2s.scrumlr.odoo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserDaoImpl userDao;

    @RequestMapping(value = "/current-user")
    public BaseUser login(final HttpSession session) {
        final BaseUser baseUser = (BaseUser) session.getAttribute(Constants.USER);

        if(Objects.nonNull(baseUser)){
            return baseUser;
        }

        throw new RuntimeException("User has not login!");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseUser login(@RequestBody final BaseUser user, final HttpSession session) {
        final List<User> users = userDao.getUsers();
        final Optional<User> loginUser =
            users
                .stream()
                .filter(u ->
                    u.getAccount().getLogin().equalsIgnoreCase(user.getAccount().getLogin())
                        && u.getAccount().getPassword().equalsIgnoreCase(user.getAccount().getPassword()))
                .findAny();

        final BaseUser currentUser = loginUser.get();
        session.setAttribute(Constants.USER, currentUser);

        return currentUser;
    }

    private BaseUser addAuthority(final BaseUser user) {
        final BaseUser baseUser = new BaseUser();
        baseUser.setAuthority("Gavin".equalsIgnoreCase(user.getName()) ? "all" : user.getName());
        baseUser.setName(user.getName());
        baseUser.setId(user.getId());

        return baseUser;
    }
}
