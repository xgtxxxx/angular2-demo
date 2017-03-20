package com.b2s.scrumlr.odoo.model;

public class OdooResponse {
    private String jsonrpc;
    private String id;
    private OdooResult result;

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

    public OdooResult getResult() {
        return result;
    }

    public void setResult(OdooResult result) {
        this.result = result;
    }
}
