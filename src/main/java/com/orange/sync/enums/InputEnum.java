package com.orange.sync.enums;

import java.util.Arrays;

public enum InputEnum {
    TimePast ,

    ;

    public  static InputEnum getInstance(String name){
        return  Arrays.stream(values()).filter(v-> v.name().equals(name)).findFirst().orElse(null);
    }



}
