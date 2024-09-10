package com.cx_project.kiiun.domain.member.service;

import com.cx_project.kiiun.domain.member.dto.request.MemberLocateReqDTO;
import com.cx_project.kiiun.domain.member.dto.request.MemberSignupReqDTO;
import com.cx_project.kiiun.global.auth.dto.AuthUserInfo;
import com.cx_project.kiiun.global.auth.dto.OAuthUserInfo;

public interface MemberService {
    public AuthUserInfo getOrRegisterUser(OAuthUserInfo oauthUserInfo);
    void signup(Long id, MemberSignupReqDTO memberSignupReqDTO);

    void saveLocate(Long memberId, MemberLocateReqDTO memberLocateReqDTO);
}
