package com.cx_project.kiiun.domain.lizard.entity;

import com.cx_project.kiiun.domain.lizard.dto.request.LizardReqDTO;
import com.cx_project.kiiun.domain.member.entity.Member;
import com.cx_project.kiiun.global.enums.Morph;
import com.cx_project.kiiun.global.enums.Species;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "lizard")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SQLDelete(sql = "UPDATE lizard SET deleted_at = now() WHERE id = ?")
public class Lizard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "lizard_name")
    private String lizardName;

    @Column(name = "adopt_date", length = 8)
    private String adoptDate;

    @Column(name = "brithDate", length = 8)
    private String birthDate;

    @Column(name = "current_weight")
    private float currentWeight;

    @Enumerated(EnumType.STRING)
    @Column(name = "species")
    private Species species;

    @Enumerated(EnumType.STRING)
    @Column(name = "morph")
    private Morph morph;

    @Column(name = "wants_mate")
    private boolean wantsMate;

    @Column(name = "opt_temperature")
    private float optTemperature;

    @Column(name = "opt_humidity")
    private float optHumidity;

    @Column(name = "haus_number")
    private String hausNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    @ColumnDefault("false")
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Builder
    public Lizard(Long id, String lizardName, String adoptDate, String birthDate, float currentWeight, Species species, Morph morph, boolean wantsMate, float optTemperature, float optHumidity, String hausNumber, Member member, LocalDateTime createdAt, LocalDateTime modifiedAt, Boolean isDeleted, LocalDateTime deletedAt) {
        this.id = id;
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
        this.member = member;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
    }


    public static Lizard fromReqDto(LizardReqDTO lizardReqDTO) {
        return Lizard.builder()
                .lizardName(lizardReqDTO.getLizardName())
                .adoptDate(lizardReqDTO.getAdoptDate())
                .birthDate(lizardReqDTO.getBirthDate())
                .currentWeight(lizardReqDTO.getCurrentWeight())
                .species(lizardReqDTO.getSpecies())
                .morph(lizardReqDTO.getMorph())
                .wantsMate(lizardReqDTO.isWantsMate())
                .optTemperature(lizardReqDTO.getOptTemperature())
                .optHumidity(lizardReqDTO.getOptHumidity())
                .hausNumber(lizardReqDTO.getHausNumber())
                .build();
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
