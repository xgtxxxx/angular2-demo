package com.b2s.scrumlr.odoo.model;

public class Kwargs {
    private Object[] args;
    private Context context;
    private Integer limit;
    private String name;
    private String operator;

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Context getContext() {
        return context;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
