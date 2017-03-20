package com.b2s.scrumlr.admin.model;

import com.b2s.scrumlr.odoo.model.OdooAccount;

import java.io.Serializable;

public class BaseUser implements Serializable {
    private static final long serialVersionUID = 8182893028471355194L;
    private String id;
    private String name;
    private OdooAccount account;
    private String authority;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public OdooAccount getAccount() {
        return account;
    }

    public void setAccount(final OdooAccount account) {
        this.account = account;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(final String authority) {
        this.authority = authority;
    }
}
