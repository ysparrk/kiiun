package com.cx_project.kiiun.domain.member.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberReqDTO {
    private Long memberId;


    @Builder
    public MemberReqDTO(Long memberId) {
        this.memberId = memberId;
    }
}
