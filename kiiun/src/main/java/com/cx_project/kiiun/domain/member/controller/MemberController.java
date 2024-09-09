package com.cx_project.kiiun.domain.member.controller;

import com.cx_project.kiiun.domain.member.dto.request.MemberSignupReqDTO;
import com.cx_project.kiiun.domain.member.service.MemberService;
import com.cx_project.kiiun.global.auth.dto.UserAuthentication;
import com.cx_project.kiiun.global.dto.ResponseDTO;
import com.cx_project.kiiun.global.service.RedisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/members")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final RedisService redisService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> signup(
            @AuthenticationPrincipal UserAuthentication userAuth,
            @Valid @RequestBody MemberSignupReqDTO memberSignupReqDTO) {
        memberService.signup(userAuth.getId(), memberSignupReqDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .message("회원가입 성공")
                        .build());
    }


}
