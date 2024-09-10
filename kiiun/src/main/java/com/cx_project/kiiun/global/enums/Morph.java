package com.cx_project.kiiun.global.enums;

public enum Morph {
    //CRESTED_GECKO
    FLAME("플레임"),
    HARLEQUIN("할리퀸"),
    PINSTRIPE("핀스트라이프"),
    DALMATIAN("달마시안"),
    TIGER("타이거"),
    PHANTOM("팬텀"),
    MOON_GLOW("문 글로우"),
    TRICOLOR("트라이컬러"),
    RED_PATTERNLESS("레드 패턴리스"),
    YELLOW_PATTERNLESS("옐로우 패턴리스"),
    LILLY_WHITE("릴리 화이트"),
    AXANTHIC("아잔틱"),

    //LEOPARD_GECKO
    NORMAL("노멀"),
    ALBINO("알비노"),
    TANGERINE("탠저린"),
    HYPOMELANISTIC("하이포멜라니스틱"),
    SUPER_HYPO("슈퍼 하이포"),
    MACK_SNOW("맥 스노우"),
    BELL_ALBINO("벨 알비노"),
    RAINWATER_ALBINO("레인워터 알비노"),
    TREMPER_ALBINO("트렘퍼 알비노"),
    BLACK_NIGHT("블랙 나이트"),
    DIABLO_BLANCO("디아블로 블랑코"),
    RAPTOR("랩터");

    private final String description;

    Morph(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
