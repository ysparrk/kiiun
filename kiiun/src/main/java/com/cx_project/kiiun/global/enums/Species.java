package com.cx_project.kiiun.global.enums;

public enum Species {
    CRESTED_GECKO("크레스티드 게코"),
    LEOPARD_GECKO("레오파드 게코");

    private final String description;

    Species(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}
