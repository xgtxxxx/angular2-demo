package com.b2s.scrumlr.odoo.model;

import com.b2s.scrumlr.admin.model.AdminUser;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class User extends AdminUser {
    private boolean success;
    private boolean shouldCreate;
    private String sessionId;
    private Integer userId;
    private boolean logged;
    private Integer resId;
    private Integer employeeId;
    private List timesheetIds;
    private String dateFrom;
    private String dateTo;
    private Integer generalAccountId;
    private Integer journalId;
    private Integer productId;
    private Integer productUomId;
    private OdooLog logs;
    private boolean submitStatus;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    private Integer company_id = 1;

    public boolean hasSessionId(){
        return StringUtils.isNotEmpty(sessionId);
    }

    public boolean hasUserId(){
        return userId!=null;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean hasResId(){
        return resId!=null;
    }

    public boolean isShouldCreate() {
        return shouldCreate;
    }

    public void setShouldCreate(boolean shouldCreate) {
        this.shouldCreate = shouldCreate;
    }

    public boolean hasContextDetail(){
        final boolean flag = employeeId!=null&&StringUtils.isNotEmpty(dateFrom)&&StringUtils.isNotEmpty(dateTo);
        return flag && timesheetIds!=null && !timesheetIds.isEmpty();
    }

    public boolean hasTimeseetContext(){
        final boolean flag = generalAccountId!=null&&journalId!=null;
        return flag&&productId!=null&&productUomId!=null;
    }

    public boolean hasProjects(){
        boolean noPorject = true;
        if(getTimesheetTasks()!=null && !getTimesheetTasks().isEmpty()){
            noPorject = false;
            for(final TimesheetTask task : getTimesheetTasks()){
                if(task.getParentAnalyticId()==null){
                    noPorject = true;
                    break;
                }
            }
        }
        return !noPorject;
    }

    public OdooLog getLogs() {
        return logs;
    }

    public void setLogs(OdooLog logs) {
        this.logs = logs;
    }

    public boolean hasTasks(){
        boolean noTask = true;
        if(getTimesheetTasks()!=null && !getTimesheetTasks().isEmpty()){
            noTask = false;
            for(final TimesheetTask task : getTimesheetTasks()){
                if(task.getTaskId()==null){
                    noTask = true;
                    break;
                }
            }
        }
        return !noTask;
    }

    public boolean hasStageId(){
        boolean no = true;
        if(getTimesheetTasks()!=null && !getTimesheetTasks().isEmpty()){
            no = false;
            for(final TimesheetTask task : getTimesheetTasks()){
                if(task.getStageId()==null){
                    no = true;
                    break;
                }
            }
        }
        return !no;
    }

    public boolean hasAccountId(){
        boolean no = true;
        if(getTimesheetTasks()!=null && !getTimesheetTasks().isEmpty()){
            no = false;
            for(final TimesheetTask task : getTimesheetTasks()){
                if(task.getAccountId()==null){
                    no = true;
                    break;
                }
            }
        }
        return !no;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public List getTimesheetIds() {
        return timesheetIds;
    }

    public void setTimesheetIds(List timesheetIds) {
        this.timesheetIds = timesheetIds;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public Integer getGeneralAccountId() {
        return generalAccountId;
    }

    public void setGeneralAccountId(Integer generalAccountId) {
        this.generalAccountId = generalAccountId;
    }

    public Integer getJournalId() {
        return journalId;
    }

    public void setJournalId(Integer journalId) {
        this.journalId = journalId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductUomId() {
        return productUomId;
    }

    public void setProductUomId(Integer productUomId) {
        this.productUomId = productUomId;
    }

    public boolean isSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(boolean submitStatus) {
        this.submitStatus = submitStatus;
    }
}
