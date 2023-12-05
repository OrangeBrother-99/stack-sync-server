package com.orange.sync.entity;

import java.util.List;

public class State {

    private List<PlayerState >  actors;
    private List  bullets;
    private  Long  nextBulletId;

    public State(List<PlayerState> actors) {
        this.actors = actors;
    }

    public List<PlayerState> getActors() {
        return actors;
    }

    public void setActors(List<PlayerState> actors) {
        this.actors = actors;
    }

    public List getBullets() {
        return bullets;
    }

    public void setBullets(List bullets) {
        this.bullets = bullets;
    }

    public Long getNextBulletId() {
        return nextBulletId;
    }

    public void setNextBulletId(Long nextBulletId) {
        this.nextBulletId = nextBulletId;
    }
}
