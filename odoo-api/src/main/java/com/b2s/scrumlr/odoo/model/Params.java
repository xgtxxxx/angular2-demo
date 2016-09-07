package com.b2s.scrumlr.odoo.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Params {
    private List args;

    private Kwargs kwargs;

    private String method;

    private String model;

    private Integer action_id;

    private Integer context_id;

    private Context context;

    private Object[] domain;

    private String[] fields;

    private Integer limit;

    private Integer offset;

    private String sort;

    public Integer getAction_id() {
        return action_id;
    }

    public void setAction_id(Integer action_id) {
        this.action_id = action_id;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List getArgs() {
        return args;
    }

    public void setArgs(List args) {
        this.args = args;
    }

    public void setArgs(Object[] args) {
        this.args = Arrays.asList(args);
    }

    public Kwargs getKwargs() {
        return kwargs;
    }

    public void setKwargs(Kwargs kwargs) {
        this.kwargs = kwargs;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getModel() {
        return model;
    }

    public Integer getContext_id() {
        return context_id;
    }

    public void setContext_id(Integer context_id) {
        this.context_id = context_id;
    }

    public Object[] getDomain() {
        return domain;
    }

    public void setDomain(Object[] domain) {
        this.domain = domain;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
