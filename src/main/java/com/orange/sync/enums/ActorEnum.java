package com.orange.sync.enums;

import java.util.Arrays;

public enum ActorEnum {
    Actor1 ,
    Actor2,
    ;

    public  static ActorEnum getInstance(String name){
        return  Arrays.stream(values()).filter(v-> v.name().equals(name)).findFirst().orElse(null);
    }



}
