package com.orange.sync.entity;

public class Vec2 {
    private  Long x;

    private  Long y;

    public Vec2(Long x, Long y) {
        this.x = x;
        this.y = y;
    }

    public Vec2() {
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }
}
