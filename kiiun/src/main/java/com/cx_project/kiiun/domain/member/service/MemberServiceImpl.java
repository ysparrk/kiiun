package com.cx_project.kiiun.domain.member.service;

import com.cx_project.kiiun.domain.member.dto.request.MemberLocateReqDTO;
import com.cx_project.kiiun.domain.member.dto.request.MemberSignupReqDTO;
import com.cx_project.kiiun.domain.member.entity.Member;
import com.cx_project.kiiun.domain.member.repository.MemberRepository;
import com.cx_project.kiiun.global.auth.dto.AuthUserInfo;
import com.cx_project.kiiun.global.auth.dto.OAuthUserInfo;
import com.cx_project.kiiun.global.kakaoMap.KakaoApiResDTO;
import com.cx_project.kiiun.global.kakaoMap.KakaoMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final KakaoMapService kakaoMapService;

    @Override
    @Transactional
    public AuthUserInfo getOrRegisterUser(OAuthUserInfo oauthUserInfo) {

        // 유저가 존재하는지 확인
        Member member = memberRepository.findByEmail(oauthUserInfo.getEmail());

        // 일단 등록은 해놓고 추가 정보 없을 시 입력하는 페이지로 이동하도록 처리
        if(member == null){
            member = Member.builder()
                    .nickname(oauthUserInfo.getNickname())
                    .email(oauthUserInfo.getEmail())
                    .profileUrl(oauthUserInfo.getProfileUrl())
                    .isDeleted(false)
                    .isActive(Boolean.FALSE)
                    .build();
            System.out.println("oauthUserInfo.getProfileUrl() = " + oauthUserInfo.getProfileUrl());
            memberRepository.save(member);
        }
        return new AuthUserInfo(member.getId(), member.getEmail(), Arrays.asList("USER"));
    }

    @Override
    @Transactional
    public void signup(Long id, MemberSignupReqDTO memberSignupReqDTO) {
        Member member = memberRepository.findById(id).orElseThrow();
        member.signup(memberSignupReqDTO);
    }

    @Override
    @Transactional
    public void saveLocate(Long memberId, MemberLocateReqDTO memberLocateReqDTO) {
        Member member = memberRepository.findById(memberId).orElseThrow();

        //카카오 지도 api를 활용해 위도, 경도 구하기
        try {
            KakaoApiResDTO.Document coordinates = kakaoMapService.getCoordinatesFromAddress(memberLocateReqDTO.getLocation());
            System.out.println(coordinates);
            member.setLocation(memberLocateReqDTO.getLocation());
            member.setLongitude(Double.parseDouble(coordinates.getX()));
            member.setLatitude(Double.parseDouble(coordinates.getY()));

        } catch (IOException e) {
            e.printStackTrace();  // 예외 발생 시 스택 트레이스를 출력
        }
    }
}
