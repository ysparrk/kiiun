package com.cx_project.kiiun.domain.member.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberLocateReqDTO {
    String location;

    @Builder
    public MemberLocateReqDTO(String location) {
        this.location = location;
    }
}
