package com.b2s.scrumlr.odoo.model;

import java.util.HashMap;

public class ResponseMap extends HashMap<String,Object> {
    private static final long serialVersionUID = 6055068159583829627L;
    private static final String SUCCESS = "success";
    private static final String MESSAGE = "message";

    public ResponseMap(){}

    public ResponseMap(final boolean success, final Object message){
        this.put(SUCCESS, success);
        this.put(MESSAGE, message);
    }

    public ResponseMap(final boolean success){
        this.put(SUCCESS, success);
    }
}
