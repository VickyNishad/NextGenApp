package com.nextgen.sms.models;

import java.util.HashMap;
import java.util.List;

public class RequestModel {
    public List<HashMap<String,Object>> payload;

    public List<HashMap<String, Object>> getPayload() {
        return payload;
    }

    public void setPayload(List<HashMap<String, Object>> payload) {
        this.payload = payload;
    }
}
