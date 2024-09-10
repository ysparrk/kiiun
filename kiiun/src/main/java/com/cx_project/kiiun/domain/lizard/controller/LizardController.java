package com.cx_project.kiiun.domain.lizard.controller;

import com.cx_project.kiiun.domain.lizard.dto.request.LizardReqDTO;
import com.cx_project.kiiun.domain.lizard.dto.response.LizardResDTO;
import com.cx_project.kiiun.domain.lizard.service.LizardService;
import com.cx_project.kiiun.global.dto.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/lizards")
@RestController
@RequiredArgsConstructor
public class LizardController {

    private final LizardService lizardService;

    @PostMapping
    public ResponseEntity<ResponseDTO> saveLizard(
            @AuthenticationPrincipal Long memberId,
            @Valid @RequestBody LizardReqDTO lizardReqDTO
            ) {

        lizardService.saveLizard(memberId, lizardReqDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .message("도마뱀 저장 성공")
                        .build());
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllLizard(
            @AuthenticationPrincipal Long memberId
    ) {

        List<LizardResDTO> lizards = lizardService.getLizards(memberId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .message("도마뱀 전체 정보 조회 성공")
                        .data(lizards)
                        .build());
    }
}
