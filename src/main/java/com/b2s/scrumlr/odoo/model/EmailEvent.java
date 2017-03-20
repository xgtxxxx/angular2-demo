package com.b2s.scrumlr.odoo.model;

import java.util.List;

public class EmailEvent {
    private final EmailType emailType;
    private final List<User> users;

    private EmailEvent(final Builder builder) {
        emailType = builder.emailType;
        users = builder.users;
    }

    public static Builder builder() {
        return new Builder();
    }

    public EmailType getEmailType() {
        return emailType;
    }

    public List<User> getUsers() {
        return users;
    }


    public static final class Builder {
        private EmailType emailType;
        private List<User> users;

        private Builder() {
        }

        public Builder withEmailType(final EmailType val) {
            emailType = val;
            return this;
        }

        public Builder withUsers(final List<User> val) {
            users = val;
            return this;
        }

        public EmailEvent build() {
            return new EmailEvent(this);
        }
    }
}
