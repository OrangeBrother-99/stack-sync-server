package com.orange.sync.entity;

import com.orange.sync.enums.ActorEnum;
import com.orange.sync.enums.BulletEnum;
import com.orange.sync.enums.WeaponEnum;

import java.util.Random;

public class PlayerState {

    private Long id;
    private Long hp;
    private String type;
    private String weaponType;
    private String bulletType;
    private Vec2 position;
    private Vec2 direction;

    public PlayerState() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHp() {
        return hp;
    }

    public void setHp(Long hp) {
        this.hp = hp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public String getBulletType() {
        return bulletType;
    }

    public void setBulletType(String bulletType) {
        this.bulletType = bulletType;
    }

    public Vec2 getPosition() {
        return position;
    }

    public void setPosition(Vec2 position) {
        this.position = position;
    }

    public Vec2 getDirection() {
        return direction;
    }

    public void setDirection(Vec2 direction) {
        this.direction = direction;
    }

    //
//    public PlayerState(Long id, Long hp, String type, String weaponType, String bulletType, Vec2 position, Vec2 direction) {
//        this.id = id;
//        this.hp = hp;
//        this.type = type;
//        this.weaponType = weaponType;
//        this.bulletType = bulletType;
//        this.position = position;
//        this.direction = direction;
//    }
    public PlayerState(Long id, Long hp, String type, String weaponType, String bulletType) {
        this.id = id;
        this.hp = hp;
        this.type = type;
        this.weaponType = weaponType;
        this.bulletType = bulletType;
    }

    public  static PlayerState create(Long id){
        PlayerState playerState = new PlayerState(id, 100L, ActorEnum.Actor1.name(), WeaponEnum.Weapon1.name(),  BulletEnum.Bullet2.name());
        playerState.setDirection(new Vec2(1L,1L));
        Random r = new Random();
        playerState.setPosition(new Vec2(-50L *(r.nextInt(4)),-50L *(r.nextInt(4))));
        return  playerState;
    }
}
