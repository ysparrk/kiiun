package com.cx_project.kiiun.domain.lizard.service;

import com.cx_project.kiiun.domain.lizard.dto.request.LizardReqDTO;
import com.cx_project.kiiun.domain.lizard.dto.response.LizardResDTO;
import com.cx_project.kiiun.domain.lizard.entity.Lizard;
import com.cx_project.kiiun.domain.lizard.repository.LizardRepository;
import com.cx_project.kiiun.domain.member.entity.Member;
import com.cx_project.kiiun.domain.member.repository.MemberRepository;
import com.cx_project.kiiun.global.enums.Morph;
import com.cx_project.kiiun.global.enums.Species;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class LizardServiceImplTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LizardRepository lizardRepository;
    @Autowired
    LizardService lizardService;

    static Member MEMBER1;
    static Lizard LIZARD1;

    @BeforeEach
    void beforeEach() {
        Member member1 = Member.builder()
                .email("사용자1@gmail.com")
                .nickname("사용자1")
                .build();

        MEMBER1 = memberRepository.save(member1);

        Lizard lizard1 = Lizard.builder()
                .member(member1)
                .lizardName("사막이")
                .species(Species.CRESTED_GECKO)
                .morph(Morph.FLAME)
                .build();

        LIZARD1 = lizardRepository.save(lizard1);
    }

    @DisplayName("도마뱀 등록")
    @Transactional
    @Test
    void saveLizard() {
        //given
        LizardReqDTO lizardReqDTO = LizardReqDTO.builder()
                .lizardName("도망이")
                .species(Species.CRESTED_GECKO)
                .morph(Morph.FLAME)
                .adoptDate("20240216")
                .birthDate("20230319")
                .optTemperature(21)
                .optHumidity(13)
                .hausNumber("defhdfdedszdr")
                .build();

        //when
        lizardService.saveLizard(MEMBER1.getId(), lizardReqDTO);

        //then
        assertThat(lizardRepository.findAllByMember(MEMBER1).size()).isEqualTo(2);
    }

    @DisplayName("도마뱀 목록 조회")
    @Test
    void getLizards() {
        //given

        //when
        List<LizardResDTO> lizards = lizardService.getLizards(MEMBER1.getId());

        //then
        assertThat(lizards.size()).isEqualTo(1);
    }
}