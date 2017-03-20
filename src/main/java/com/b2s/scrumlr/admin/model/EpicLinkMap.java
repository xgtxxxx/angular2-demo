package com.b2s.scrumlr.admin.model;

public class EpicLinkMap {
    private String epicName;
    private String odooProject;
    private boolean active;

    public String getEpicName() {
        return epicName;
    }

    public void setEpicName(String epicName) {
        this.epicName = epicName;
    }

    public String getOdooProject() {
        return odooProject;
    }

    public void setOdooProject(String odooProject) {
        this.odooProject = odooProject;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
