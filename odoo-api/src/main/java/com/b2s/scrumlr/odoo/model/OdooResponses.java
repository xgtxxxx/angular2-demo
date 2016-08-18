package com.b2s.scrumlr.odoo.model;

public class OdooResponses {
    private String jsonrpc;
    private String id;
    private OdooResults[] result;

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

    public OdooResults[] getResult() {
        return result;
    }

    public void setResult(OdooResults[] result) {
        this.result = result;
    }
}
