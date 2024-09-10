package com.cx_project.kiiun.domain.member.service;

import com.cx_project.kiiun.domain.member.dto.request.MemberLocateReqDTO;
import com.cx_project.kiiun.domain.member.entity.Member;
import com.cx_project.kiiun.domain.member.repository.MemberRepository;
import com.cx_project.kiiun.global.kakaoMap.KakaoMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    KakaoMapService kakaoMapService;

    static Member MEMBER1;


    @BeforeEach
    void beforeEach() {
        Member member1 = Member.builder()
                .email("사용자1@gmail.com")
                .nickname("사용자1")
                .build();

        MEMBER1 = memberRepository.save(member1);
    }

    @DisplayName("주소 정보 추가 후 카카오API를 활용해 위도/경도 값 저장")
    @Transactional
    @Test
    void saveLocate() {
        //given
        MemberLocateReqDTO memberLocateReqDTO = MemberLocateReqDTO.builder()
                .location("경기 성남시 분당구 판교동")
                .build();

        //when
        memberService.saveLocate(MEMBER1.getId(), memberLocateReqDTO);

        //then
        assertThat(MEMBER1.getLongitude()).isEqualTo(127.09878136729);
        assertThat(MEMBER1.getLatitude()).isEqualTo(37.3898680691971);
    }
}