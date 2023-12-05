package com.orange.sync.entity;

import java.math.BigDecimal;

public class TimePast {
    private  BigDecimal dt;
    private  String type;

    public TimePast(BigDecimal dt, String type) {
        this.dt = dt;
        this.type = type;
    }

    public TimePast() {
    }

    public BigDecimal getDt() {
        return dt;
    }

    public void setDt(BigDecimal dt) {
        this.dt = dt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
