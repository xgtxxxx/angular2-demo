package com.b2s.scrumlr.odoo.service.impl;

import com.b2s.scrumlr.odoo.model.*;
import com.b2s.scrumlr.odoo.service.OdooService;
import com.b2s.scrumlr.odoo.service.TimesheetService;
import com.b2s.scrumlr.odoo.utils.JsonUtil;
import org.apache.commons.codec.language.DoubleMetaphone;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.security.SignatureException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OdooServiceImpl implements OdooService {
    private final Logger LOG = LoggerFactory.getLogger(OdooServiceImpl.class);

    @Autowired
    private RestTemplate restClient;

    @Override
    public synchronized User getSessionId(final User user) throws Exception {
        if(user.hasSessionId()){
            return user;
        }
        final String url = "https://odoo.bridge2solutions.net/web/login";

        final MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("login", user.getAccount().getLogin());
        map.add("password", user.getAccount().getPassword());
        final String sessionId = this.doRequestPost("getSessionId()", url, map, null);
        user.setSessionId(sessionId);
        return user;
    }

    @Override
    public synchronized User getUserId(User user) throws Exception {
        if(user.hasUserId()){
            return user;
        }
        final String url = "https://odoo.bridge2solutions.net/web/session/get_session_info";
        final String json = this.doRequestPost("getUserId()", url, new RequestBody(), user.getSessionId());
        final OdooResponse response = JsonUtil.fromJson(json, OdooResponse.class);
        user.setUserId(response.getResult().getUid());
        return user;
    }

    @Override
    public synchronized User getCurrentTimeSheetResId(User user) throws Exception {
        if(user.hasResId()){
            return user;
        }

        if(StringUtils.isEmpty(user.getDate()) || user.getDate().equals(DateFormatUtils.format(new Date(),"yyyy-MM-dd"))){
            final String url = "https://odoo.bridge2solutions.net/web/action/run";
            final RequestBody requestBody = new RequestBody();
            final Params params = new Params();
            params.setAction_id(398);
            final Context context = new Context();
            context.setUid(user.getUserId());
            context.addParam("action",398);
            params.setContext(context);
            requestBody.setParams(params);
            final String json = this.doRequestPost("getCurrentTimeSheetResId",url, requestBody, user.getSessionId());
            final OdooResponse response = JsonUtil.fromJson(json, OdooResponse.class);
            if(response.getResult().getRes_id()==null){
                user.setShouldCreate(true);
                initDateToUser(user);
            }else{
                user.setShouldCreate(false);
                user.setResId(response.getResult().getRes_id());
            }
        }else{
            final String url = "https://odoo.bridge2solutions.net/web/dataset/search_read";
            final RequestBody requestBody = new RequestBody();
            final Params params = new Params();
            final Context context = new Context();
            context.setUid(user.getUserId());
            context.setParams(new HashMap<String, Object>());
            params.setContext(context);
            params.setDomain(new Object[]{new Object[]{"user_id", "=", user.getUserId()}});
            params.setFields(new String[]{"employee_id","date_from","date_to","department_id","total_attendance","total_timesheet","total_difference","state"});
            params.setLimit(80);
            params.setOffset(0);
            params.setSort("");
            params.setModel("hr_timesheet_sheet.sheet");
            requestBody.setParams(params);
            final String json = this.doRequestPost("getCurrentTimeSheetResId",url, requestBody, user.getSessionId());
            final OdooResponse response = JsonUtil.fromJson(json, OdooResponse.class);
            if(response.getResult().getRecords()!=null && !response.getResult().getRecords().isEmpty()){
                for(final OdooRecord record: response.getResult().getRecords()){
                    if(isDateIn(user.getDate(), record.getDate_from(), record.getDate_to())){
                        user.setResId(record.getId());
                        break;
                    }
                }
            }
        }
        return user;
    }

    private boolean isDateIn(final String date, final String date_from, final String date_to) {
        try {
            final long now = DateUtils.parseDate(date,"yyyy-MM-dd").getTime();
            final long start = DateUtils.parseDate(date_from,"yyyy-MM-dd").getTime();
            final long end = DateUtils.parseDate(date_to,"yyyy-MM-dd").getTime();
            return now<=end && now>=start;
        } catch (final ParseException e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

    private static void initDateToUser(final User user) {
        final GregorianCalendar cal = new GregorianCalendar();
        final Date now = new Date();
        cal.setTime(now);
        cal.setFirstDayOfWeek(GregorianCalendar.MONDAY);   // 设置一个星期的第一天为星期1，默认是星期日
        cal.set(GregorianCalendar.DAY_OF_WEEK,GregorianCalendar.MONDAY);
        final Date dateFrom = cal.getTime();
        cal.set(GregorianCalendar.DAY_OF_WEEK,GregorianCalendar.SUNDAY);
        final Date dateTo = cal.getTime();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        user.setDateFrom(sdf.format(dateFrom));
        user.setDateTo(sdf.format(dateTo));
    }

    @Override
    public synchronized User getContextDetail(User user) throws Exception {
        return getContextDetail(user,false);
    }

    @Override
    public synchronized User getContextDetail(User user, boolean force) throws Exception {
        if(user.isShouldCreate()){
            return user;
        }
        if(!force){
            if(user.hasContextDetail()){
                return user;
            }
        }
        final String url = "https://odoo.bridge2solutions.net/web/dataset/call_kw/hr_timesheet_sheet.sheet/read";
        final RequestBody body = new RequestBody();
        final Object[] args = new Object[2];
        args[0] = new int[]{user.getResId()};
        args[1] = new String[]{"attendance_count",
                "timesheet_ids",
                "message_follower_ids",
                "attendances_ids",
                "company_id",
                "state_attendance",
                "employee_id",
                "user_id",
                "date_from",
                "period_ids",
                "message_ids",
                "state",
                "is_uid_manager",
                "department_id",
                "total_attendance",
                "date_to",
                "total_difference",
                "name",
                "total_timesheet",
                "timesheet_activity_count",
                "is_uid",
                "display_name"};
        final Params params = new Params();
        params.setArgs(args);
        final Kwargs kwargs = new Kwargs();
        final Context context = new Context();
        context.setParams(new HashMap<String, Object>());
        context.setUid(user.getUserId());
        kwargs.setContext(context);
        params.setKwargs(kwargs);
        params.setMethod("read");
        params.setModel("hr_timesheet_sheet.sheet");
        body.setParams(params);
        final String json = this.doRequestPost("getContextDetail",url,body,user.getSessionId());
        final OdooResponses response = JsonUtil.fromJson(json, OdooResponses.class);
        final OdooResults result = response.getResult()[0];
        user.setEmployeeId((Integer)(result.getEmployee_id()[0]));
        user.setTimesheetIds(result.getTimesheet_ids()==null||result.getTimesheet_ids().isEmpty()?result.getPeriod_ids():result.getTimesheet_ids());
        user.setDateFrom(result.getDate_from());
        user.setDateTo(result.getDate_to());
        return user;
    }

    @Override
    public synchronized User getTimeSheetContext(User user) throws Exception {
        if(user.hasTimeseetContext()){
            return user;
        }
        final String url = "https://odoo.bridge2solutions.net/web/dataset/call_kw/hr.analytic.timesheet/default_get";
        final RequestBody body = new RequestBody();
        final Object[] args = new Object[2];
        args[0] = new String[]{"parent_analytic_id", "account_id", "task_id", "general_account_id", "journal_id", "date", "name", "user_id", "product_id", "product_uom_id", "to_invoice", "amount", "unit_amount", "stage_id", "to_invoice","date_from","date_to"};
        final Context c = new Context();
        c.setUid(user.getUserId());
        c.setUser_id(user.getUserId());
        args[1] = c;
        final Params params = new Params();
        params.setArgs(args);
        params.setKwargs(new Kwargs());
        params.setMethod("default_get");
        params.setModel("hr.analytic.timesheet");
        body.setParams(params);
        final String json = this.doRequestPost("getTimeSheetContext",url,body,user.getSessionId());
        final OdooResponse response = JsonUtil.fromJson(json, OdooResponse.class);
        user.setProductId(response.getResult().getProduct_id());
        user.setProductUomId(response.getResult().getProduct_uom_id());
        user.setJournalId(response.getResult().getJournal_id());
        user.setGeneralAccountId(response.getResult().getGeneral_account_id());
        return user;
    }

    @Override
    public synchronized User getProjects(User user) throws Exception {
        if(user.hasProjects()){
            return user;
        }
        final String url = "https://odoo.bridge2solutions.net/web/dataset/call_kw/account.analytic.account/name_search";
        final RequestBody body = new RequestBody();
        final Params params = new Params();
        params.setArgs(new ArrayList());
        final Kwargs kwargs = new Kwargs();
        final Object[] args = new Object[6];
        args[0] = new Object[]{"type", "in", new String[]{"contract"}};
        args[1] = new Object[]{"use_timesheets", "=", 1};
        args[2] = new Object[]{"is_proj_member", "=", 1};
        args[3] = new Object[]{"is_template", "=", 0};
        args[4] = new Object[]{"timesheet_view", "=", 1};
        args[5] = new Object[]{"state", "<>", "close"};
        kwargs.setArgs(args);
        final Context context = new Context();
        context.setUid(user.getUserId());
        context.setDefault_use_timesheets(1);
        context.setDefault_type("contract");
        kwargs.setContext(context);
        kwargs.setLimit(15);
        kwargs.setName("");
        kwargs.setOperator("ilike");
        params.setKwargs(kwargs);
        params.setMethod("name_search");
        params.setModel("account.analytic.account");
        body.setParams(params);
        final String json = this.doRequestPost("getProjects",url,body,user.getSessionId());
        final OdooResponseOfProject responses = JsonUtil.fromJson(json, OdooResponseOfProject.class);
        return matchProjectToUser(user, responses.getResult());
    }

    private User matchProjectToUser(User user, List<List<Object>> result) {
        for(final TimesheetTask task : user.getTimesheetTasks()){
            boolean isMatch = false;
            for(final List<Object> project: result){
                final String pn = (String)project.get(1);
                if(pn.toLowerCase().indexOf(task.getProject().toLowerCase())!=-1){
                    task.setProjectId((Integer)project.get(0));
                    task.setProjectName(pn);
                    isMatch = true;
                    break;
                }
            }
            if(!isMatch){
                task.setProjectId((Integer)result.get(0).get(0));
                task.setProjectName((String)result.get(0).get(1));
            }
        }
        return user;
    }

    @Override
    public synchronized User getTasksByProject(User user) throws Exception {
        if(user.hasTasks()){
            return user;
        }
        final String url = "https://odoo.bridge2solutions.net/web/dataset/call_kw/project.task/name_search";
        for(final TimesheetTask task : user.getTimesheetTasks()){
            final RequestBody body = new RequestBody();
            final Params params = new Params();
            params.setArgs(new ArrayList());
            final Kwargs kwargs = new Kwargs();
            final Object[] args = new Object[3];
            args[0] = new Object[]{"project_id.analytic_account_id", "=", task.getProjectId()};
            args[1] = new Object[]{"stage_id.name", "not in", new String[]{"Not Started", "Done", "Cancelled"}};
            args[2] = new Object[]{"id", "not in", new int[]{0}};
            kwargs.setArgs(args);
            final Context context = new Context();
            context.setUid(user.getUserId());
            context.setAccount_id(task.getProjectId());
            kwargs.setContext(context);
            kwargs.setLimit(15);
            kwargs.setName("");
            kwargs.setOperator("ilike");
            params.setKwargs(kwargs);
            params.setMethod("name_search");
            params.setModel("project.task");
            body.setParams(params);
            final String json = this.doRequestPost("getTasksByProject",url,body,user.getSessionId());
            final OdooResponseOfProject response = JsonUtil.fromJson(json, OdooResponseOfProject.class);
            matchTaskToProject(task, response.getResult());
        }
        return user;
    }

    private void matchTaskToProject(TimesheetTask task, List<List<Object>> result) {
        boolean isMatch = false;
        for(final List<Object> t : result){
            final String tv = (String)t.get(1);
            if(tv.toLowerCase().indexOf(task.getTask().toLowerCase())!=-1){
                task.setTaskId((Integer)t.get(0));
                task.setTaskName(tv);
                isMatch = true;
                break;
            }
        }
        if(!isMatch){
            task.setTaskId((Integer)result.get(0).get(0));
            task.setTaskName((String)result.get(0).get(1));
        }
    }

    @Override
    public synchronized User getStageId(User user) throws Exception {
        if(user.hasStageId()){
            return user;
        }
        final String url = "https://odoo.bridge2solutions.net/web/dataset/call_kw/hr.analytic.timesheet/onchange_task_id";
        for(final TimesheetTask task : user.getTimesheetTasks()) {
            final RequestBody body = new RequestBody();
            final Params params = new Params();
            final Object[] args = new Object[2];
            args[1] = task.getTaskId();
            params.setArgs(args);
            params.setKwargs(new Kwargs());
            params.setMethod("onchange_task_id");
            params.setModel("hr.analytic.timesheet");
            body.setParams(params);
            final String json = this.doRequestPost("getStageId",url,body,user.getSessionId());
            final OdooResponse response = JsonUtil.fromJson(json, OdooResponse.class);
            task.setStageId(response.getResult().getValue().getStage_id());
        }
        return user;
    }

    @Override
    public synchronized User getAccountId(User user) throws Exception {
        if(user.hasAccountId()){
            return user;
        }
        final String url = "https://odoo.bridge2solutions.net/web/dataset/call_kw/hr.analytic.timesheet/onchange_stage_id";
        for(final TimesheetTask task : user.getTimesheetTasks()) {
            final RequestBody body = new RequestBody();
            final Params params = new Params();
            final Object[] args = new Object[3];
            args[1] = task.getTaskId();
            args[2] = task.getStageId();
            params.setArgs(args);
            params.setKwargs(new Kwargs());
            params.setMethod("onchange_stage_id");
            params.setModel("hr.analytic.timesheet");
            body.setParams(params);
            final String json = this.doRequestPost("getAccountId",url,body,user.getSessionId());
            final OdooResponse response = JsonUtil.fromJson(json, OdooResponse.class);
            task.setAccountId(response.getResult().getValue().getAccount_id());
        }
        return user;
    }

    @Override
    public synchronized User addTimeToCurrentTimeSheet(User user) throws Exception {
        final String url = "https://odoo.bridge2solutions.net/web/dataset/call_kw/hr_timesheet_sheet.sheet/write";
        final RequestBody body = new RequestBody();
        final Params params = new Params();
        final Object[] args = new Object[2];
        args[0] = new Object[]{user.getResId()};
        final Map a1 = new HashMap<String,Object>();
        a1.put("timesheet_ids",createTimesheetTasks(user));
        args[1] = a1;
        params.setArgs(args);
        final Kwargs kwargs = new Kwargs();
        final Context context = new Context();
        context.setUid(user.getUserId());
        context.setParams(new HashMap<String, Object>());
        kwargs.setContext(context);
        params.setKwargs(kwargs);
        params.setMethod("write");
        params.setModel("hr_timesheet_sheet.sheet");
        body.setParams(params);
        final String json = this.doRequestPost("addTimeToCurrentTimeSheet",url,body,user.getSessionId());
        final OdooResponseOfResult response = JsonUtil.fromJson(json, OdooResponseOfResult.class);
        if(response.getResult()){
            user.setSuccess(true);
        }else{
            user.setSuccess(false);
            LOG.error("Log time to user "+user.getName()+" failed!");
            throw new Exception("Log time to user "+user.getName()+" failed!");
        }
        return user;
    }

    private Object createTimesheetTasks(final User user) {
        final Object[] objs = new Object[user.getTimesheetTasks().size()];
        int i = 0;
        for(final TimesheetTask task:user.getTimesheetTasks()){
            final Object[] to = new Object[3];
            to[0] = 0;
            to[1] = false;
            final Map<String,Object> tm = new HashMap<>();
            tm.put("account_id", task.getAccountId());
            tm.put("amount",0);
            tm.put("date",task.getCurrentDate());
            tm.put("general_account_id",user.getGeneralAccountId());
            tm.put("journal_id",user.getJournalId());
            tm.put("name","/");
            tm.put("parent_analytic_id",task.getProjectId());
            tm.put("product_id",user.getProductId());
            tm.put("product_uom_id",user.getProductUomId());
            tm.put("stage_id",task.getStageId());
            tm.put("task_id",task.getTaskId());
            tm.put("to_invoice",false);
            tm.put("unit_amount",task.getHours());
            tm.put("user_id",user.getUserId());
            to[2] = tm;
            objs[i] = to;
            i++;
        }
        return objs;
    }

    @Override
    public synchronized User createAndLogTimeSheet(User user) throws Exception {
        final String url = "https://odoo.bridge2solutions.net/web/dataset/call_kw/hr_timesheet_sheet.sheet/create";
        final RequestBody body = new RequestBody();
        final Params params = new Params();
        final Map<String,Object> args = new HashMap<>();
        args.put("date_from",user.getDateFrom());
        args.put("date_to",user.getDateTo());
        args.put("name",false);
        args.put("department_id",false);
        args.put("company_id",user.getCompany_id());
        args.put("attendances_ids",new ArrayList());
        args.put("message_follower_ids",false);
        args.put("message_ids",false);
        args.put("timesheet_ids",createTimesheetTasks(user));
        params.setArgs(new Object[]{args});
        final Kwargs kwargs = new Kwargs();
        final Context context = new Context();
        context.setUid(user.getUserId());
        context.setParams(new HashMap<String, Object>());
        kwargs.setContext(context);
        params.setKwargs(kwargs);
        params.setMethod("create");
        params.setModel("hr_timesheet_sheet.sheet");
        body.setParams(params);
        final String json = this.doRequestPost("createAndLogTimeSheet",url,body,user.getSessionId());
        final OdooResponseOfResult response = JsonUtil.fromJson(json, OdooResponseOfResult.class);
        if(response.getResult()){
            user.setSuccess(true);
        }else{
            user.setSuccess(false);
            LOG.error("create timesheet to user "+user.getName()+" failed!");
            throw new Exception("create timesheet to user "+user.getName()+" failed!");
        }
        return user;
    }

    @Override
    public synchronized User getCurrentTimeSheetDetail(final User user) throws Exception {
        return getCurrentTimeSheetDetail(user, false);
    }

    @Override
    public synchronized User getCurrentTimeSheetDetail(final User user, final boolean isFull) throws Exception {
        final String url = "https://odoo.bridge2solutions.net/web/dataset/call_kw/hr.analytic.timesheet/read";
        final RequestBody body = new RequestBody();
        final Params params = new Params();
        final List<Object> args = new ArrayList<>();
        args.add(user.getTimesheetIds());
        args.add(new String[]{"date", "parent_analytic_id", "task_id", "stage_id", "account_id", "name", "unit_amount", "journal_id","product_id","product_uom_id","general_account_id","user_id","to_invoice"});
        params.setArgs(args);
        final Kwargs kwargs = new Kwargs();
        final Context context = new Context();
        context.setUid(user.getUserId());
        context.setEmployee_id(user.getEmployeeId());
        context.setUser_id(user.getUserId());
        context.setTimesheet_date_from(user.getDateFrom());
        context.setTimesheet_date_to(user.getDateTo());
        kwargs.setContext(context);
        params.setKwargs(kwargs);
        params.setMethod("read");
        params.setModel("hr.analytic.timesheet");
        body.setParams(params);
        final String json = this.doRequestPost("getCurrentTimeSheetDetail",url,body,user.getSessionId());
        final OdooResponses responses = JsonUtil.fromJson(json, OdooResponses.class);
        findLogsToUser(user,isFull,responses.getResult());
        return user;
    }

    @Override
    public synchronized User submitToManager(final User user) throws Exception {
        final String url = "https://odoo.bridge2solutions.net/web/dataset/call_button";
        final RequestBody body = new RequestBody();
        final Params params = new Params();
        final List<Object> args = new ArrayList<>();
        args.add(new Object[]{user.getResId()});
        final Context context = new Context();
        context.setUid(user.getUserId());
        context.setParams(new HashMap<String, Object>());
        args.add(context);
        params.setArgs(args);
        params.setContext_id(1);
        params.setMethod("button_confirm");
        params.setModel("hr_timesheet_sheet.sheet");
        body.setParams(params);
        this.doRequestPost("submitToManager",url,body,user.getSessionId());
        return getSubmitStatus(user);
    }

    @Override
    public User getSubmitStatus(final User user) throws Exception {
        final String url = "https://odoo.bridge2solutions.net/web/dataset/call_kw/hr_timesheet_sheet.sheet/search_read";
        final RequestBody body = new RequestBody();
        final Params params = new Params();
        final List<Object> args = new ArrayList<>();
        args.add(new Object[]{new Object[]{"id","in",new Object[]{user.getResId()}}});
        args.add(new String[]{"attendance_count","timesheet_ids","message_follower_ids","attendances_ids","company_id","state_attendance","employee_id","user_id","date_from","period_ids","message_ids","state","is_uid_manager","department_id","total_attendance","date_to","total_difference","name","total_timesheet","timesheet_activity_count","is_uid","display_name"});
        params.setArgs(args);
        final Kwargs kwargs = new Kwargs();
        final Context context = new Context();
        context.setUid(user.getUserId());
        context.setParams(new HashMap<String, Object>());
        context.setActive_test(false);
        kwargs.setContext(context);
        params.setKwargs(kwargs);
        params.setMethod("search_read");
        params.setModel("hr_timesheet_sheet.sheet");
        body.setParams(params);
        final String json = this.doRequestPost("getSubmitStatus",url,body,user.getSessionId());
        final OdooResponses response = JsonUtil.fromJson(json, OdooResponses.class);
        final String state = response.getResult()[0].getState();
        if("confirm".equalsIgnoreCase(state)){
            user.setSubmitStatus(true);
        }else{
            user.setSubmitStatus(false);
        }
        return user;
    }

    private void findLogsToUser(final User user,final boolean isFull,final OdooResults[] result) {
        final OdooLog logs = new OdooLog(user.getName(),true,true);
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String today = sdf.format(new Date());
        final Map<String,Double> map = new HashMap<>();
        for(final OdooResults or: result){
            if(isFull){
                Double hours = map.get(or.getDate());
                if(hours==null){
                    hours = 0.0;
                }
                hours += or.getUnit_amount();
                map.put(or.getDate(),hours);
            }else{
                if(today.equals(or.getDate())){
                    logs.addLogBody(new LogBody(or.getUnit_amount(),(String)or.getParent_analytic_id()[1], (String)or.getTask_id()[1]));
                    logs.setLoged(true);
                }
            }
        }
        if(isFull){
            final List<String> dates = new ArrayList<>();
            dates.addAll(map.keySet());
            Collections.sort(dates);
            for(final String date : dates){
                logs.addLogBody(new LogBody(map.get(date),date,""));
            }
        }
        user.setLogs(logs);
    }

    private String doRequestPost(final String methodName, final String url, final Object jsonObject, final String sessionId) throws Exception {
        String body = null;
        if (jsonObject != null) {
            body = JsonUtil.toJson(jsonObject);
        }

        if (LOG.isInfoEnabled()) {
            LOG.info("Request[{}] url:{}, session_id:{}", methodName, url, sessionId);
            if (StringUtils.isNotEmpty(body)) {
                LOG.info("Request[{}] body:{}", methodName, body);
            } else {
                LOG.info("Request[{}] body:{}", methodName, "N/A");
            }
        }
        String content = null;
        if(jsonObject instanceof MultiValueMap){
            ResponseEntity<String> response = restClient.exchange(url, HttpMethod.POST, createLoginEntity((MultiValueMap) jsonObject), String.class);
            content = findSessionId(response.getHeaders());
        }else{
            final ResponseEntity<String> responseEntity = restClient.exchange(
                    url,
                    HttpMethod.POST,
                    createEntity(sessionId, body),
                    String.class);
            content = responseEntity.getBody();
        }
        if (LOG.isInfoEnabled()) {
            LOG.info("Response[{}]:{}", methodName, content);
        }
        return content;
    }

    private String findSessionId(final HttpHeaders headers) {
        final List<String> values = headers.get("Set-Cookie");
        String v = null;
        for(final String value: values){
            if(value.indexOf("session_id")!=-1){
                v = value;
            }
        }
        final String[] vs = v.split(";");
        final String keyvalue = vs[0];
        final String[] kvs = keyvalue.split("=");
        return kvs[1];
    }

    private HttpEntity<String> createEntity(final String sessionId, final String requestBody) throws SignatureException {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Cookie", "session_id="+sessionId);
        final HttpEntity<String> entity;
        if (StringUtils.isEmpty(requestBody)) {
            entity = new HttpEntity<String>(headers);
        } else {
            headers.setContentType(MediaType.APPLICATION_JSON);
            entity = new HttpEntity<String>(requestBody, headers);
        }
        return entity;
    }

    private HttpEntity<MultiValueMap> createLoginEntity(final MultiValueMap map) throws SignatureException {
        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<MultiValueMap> entity;
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        entity = new HttpEntity<MultiValueMap>(map, headers);
        return entity;
    }

}
