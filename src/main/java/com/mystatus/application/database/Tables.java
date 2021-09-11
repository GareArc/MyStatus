package com.mystatus.application.database;

import lombok.Getter;

@Getter
public enum Tables {
    OVERVIEW("mystatus_overview"),
    ECO("mystatus_eco");


    private final String name;

    Tables(String name){
        this.name = name;
    }
}
