package com.cx_project.kiiun.domain.lizard.dto.response;

import com.cx_project.kiiun.global.enums.Morph;
import com.cx_project.kiiun.global.enums.Species;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LizardMatingResDTO {
    //member 정보
    private String nickname;
    private String location;

    //lizard 정보
    private String lizardName;
    private String adoptDate;
    private String birthDate;
    private float currentWeight;
    private Species species;
    private Morph morph;

    @Builder
    public LizardMatingResDTO(String nickname, String location, String lizardName, String adoptDate, String birthDate, float currentWeight, Species species, Morph morph) {
        this.nickname = nickname;
        this.location = location;
        this.lizardName = lizardName;
        this.adoptDate = adoptDate;
        this.birthDate = birthDate;
        this.currentWeight = currentWeight;
        this.species = species;
        this.morph = morph;
    }
}
