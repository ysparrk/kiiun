package com.cx_project.kiiun.domain.member.service;

import com.cx_project.kiiun.global.auth.dto.AuthUserInfo;
import com.cx_project.kiiun.global.auth.dto.OAuthUserInfo;

public interface MemberService {
    public AuthUserInfo getOrRegisterUser(OAuthUserInfo oauthUserInfo);
}
