package com.b2s.scrumlr.odoo.model;

import java.util.List;

public class OdooResponseOfProject {
    private String jsonrpc;
    private String id;
    private List<List<Object>> result;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<List<Object>> getResult() {
        return result;
    }

    public void setResult(List<List<Object>> result) {
        this.result = result;
    }
}
