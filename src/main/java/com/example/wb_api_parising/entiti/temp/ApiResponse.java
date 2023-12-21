package com.example.wb_api_parising.entiti.temp;

import java.util.Map;

public class ApiResponse {
    private int state;
    private int version;
    private Map<String, Object> params;
    private Data data;

    public ApiResponse(int state, int version, Map<String, Object> params, Data data) {
        this.state = state;
        this.version = version;
        this.params = params;
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
