package com.orange.sync.enums;

import java.util.Arrays;

public enum WeaponEnum {
    Weapon1 ,
    Weapon2 ,
    ;

    public  static WeaponEnum getInstance(String name){
        return  Arrays.stream(values()).filter(v-> v.name().equals(name)).findFirst().orElse(null);
    }



}
