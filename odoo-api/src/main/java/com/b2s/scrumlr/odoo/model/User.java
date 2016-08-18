package com.b2s.scrumlr.odoo.model;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class User {
    private boolean success;

    private boolean shouldCreate;

    private String name;

    private OdooAccount account;

    private String sessionId;

    private Integer userId;

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

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    private Integer company_id = 1;

    private List<TimesheetTask> timesheetTasks;

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
        if(timesheetTasks!=null && !timesheetTasks.isEmpty()){
            noPorject = false;
            for(final TimesheetTask task : timesheetTasks){
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
        if(timesheetTasks!=null && !timesheetTasks.isEmpty()){
            noTask = false;
            for(final TimesheetTask task : timesheetTasks){
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
        if(timesheetTasks!=null && !timesheetTasks.isEmpty()){
            no = false;
            for(final TimesheetTask task : timesheetTasks){
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
        if(timesheetTasks!=null && !timesheetTasks.isEmpty()){
            no = false;
            for(final TimesheetTask task : timesheetTasks){
                if(task.getAccountId()==null){
                    no = true;
                    break;
                }
            }
        }
        return !no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OdooAccount getAccount() {
        return account;
    }

    public void setAccount(OdooAccount account) {
        this.account = account;
    }

    public List<TimesheetTask> getTimesheetTasks() {
        return timesheetTasks;
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

    public void setTimesheetTasks(List<TimesheetTask> timesheetTasks) {
        this.timesheetTasks = timesheetTasks;
    }
}
