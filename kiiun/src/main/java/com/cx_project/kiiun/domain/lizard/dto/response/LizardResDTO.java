package com.cx_project.kiiun.domain.lizard.dto.response;

import com.cx_project.kiiun.global.enums.Morph;
import com.cx_project.kiiun.global.enums.Species;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LizardResDTO {
    private Long lizardId;
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
    public LizardResDTO(Long lizardId, String lizardName, String adoptDate, String birthDate, float currentWeight, Species species, Morph morph, boolean wantsMate, float optTemperature, float optHumidity, String hausNumber) {
        this.lizardId = lizardId;
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
