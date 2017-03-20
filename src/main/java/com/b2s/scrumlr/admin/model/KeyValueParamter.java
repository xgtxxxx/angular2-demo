package com.b2s.scrumlr.admin.model;

import com.b2s.scrumlr.odoo.utils.JsonUtil;

import java.io.Serializable;

public class KeyValueParamter implements Serializable{
    private static final long serialVersionUID = 2893337376991284303L;

    private String key;
    private Object value;

    public KeyValueParamter(String key,Object value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public String getValueAsString() {
        return JsonUtil.toJson(value);
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
