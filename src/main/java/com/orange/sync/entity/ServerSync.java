package com.orange.sync.entity;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class ServerSync {
    private List<JSONObject> list;
    private Long frameId;

    public ServerSync(List<JSONObject> list, Long frameId) {
        this.list = list;
        this.frameId = frameId;
    }

    public List<JSONObject> getList() {
        return list;
    }

    public void setList(List<JSONObject> list) {
        this.list = list;
    }

    public Long getFrameId() {
        return frameId;
    }

    public void setFrameId(Long frameId) {
        this.frameId = frameId;
    }
}
