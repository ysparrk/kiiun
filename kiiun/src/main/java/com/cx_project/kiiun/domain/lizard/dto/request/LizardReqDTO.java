package com.cx_project.kiiun.domain.lizard.dto.request;

import com.cx_project.kiiun.global.enums.Morph;
import com.cx_project.kiiun.global.enums.Species;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LizardReqDTO {
    private Long memberId;
    private String lizardName;
    private String adoptDate;
    private String birthDate;
    private float currentWeight;
    private Species species;
    private Morph morph;
    private boolean wantsMate;
    private float optTemperature;
    private float optHumidity;
    private String hausNumber;


    @Builder
    public LizardReqDTO(Long memberId, String lizardName, String adoptDate, String birthDate, float currentWeight, Species species, Morph morph, boolean wantsMate, float optTemperature, float optHumidity, String hausNumber) {
        this.memberId = memberId;
        this.lizardName = lizardName;
        this.adoptDate = adoptDate;
        this.birthDate = birthDate;
        this.currentWeight = currentWeight;
        this.species = species;
        this.morph = morph;
        this.wantsMate = wantsMate;
        this.optTemperature = optTemperature;
        this.optHumidity = optHumidity;
        this.hausNumber = hausNumber;
    }
}
