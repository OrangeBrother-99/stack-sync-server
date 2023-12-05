package com.orange.sync.enums;

import java.util.Arrays;

public enum BulletEnum {
    Bullet1 ,
    Bullet2 ,
    ;

    public  static BulletEnum getInstance(String name){
        return  Arrays.stream(values()).filter(v-> v.name().equals(name)).findFirst().orElse(null);
    }



}
