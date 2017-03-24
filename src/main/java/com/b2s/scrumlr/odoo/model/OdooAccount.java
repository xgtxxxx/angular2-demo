package com.b2s.scrumlr.odoo.model;

import com.b2s.scrumlr.admin.model.AdminUser;
import com.fasterxml.jackson.annotation.JsonView;

public class OdooAccount {
    @JsonView(AdminUser.WithoutPasswordView.class)
    private String login;
    @JsonView(AdminUser.WithPasswordView.class)
    private String password;

    public OdooAccount() {
    }

    public OdooAccount(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
