package com.cx_project.kiiun.domain.lizard.service;

import com.cx_project.kiiun.domain.lizard.dto.request.LizardReqDTO;
import com.cx_project.kiiun.domain.lizard.dto.response.LizardMatingResDTO;
import com.cx_project.kiiun.domain.lizard.dto.response.LizardResDTO;


import java.util.List;

public interface LizardService {
    void saveLizard(Long memberId, LizardReqDTO lizardReqDTO);
    List<LizardResDTO> getLizards(Long memberId);
    List<LizardMatingResDTO> getMatings(Long memberId);
}
