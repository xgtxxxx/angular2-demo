package com.b2s.scrumlr.admin.model;

import java.util.List;

public class Customs {
    private boolean active;

    private List<Project> projects;

    public List<Project> getProjects() {
        return projects;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
