package com.cx_project.kiiun.domain.lizard.service;

import com.cx_project.kiiun.domain.lizard.dto.request.LizardReqDTO;
import com.cx_project.kiiun.domain.lizard.dto.response.LizardResDTO;
import com.cx_project.kiiun.domain.lizard.entity.Lizard;
import com.cx_project.kiiun.domain.lizard.repository.LizardRepository;
import com.cx_project.kiiun.domain.member.entity.Member;
import com.cx_project.kiiun.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LizardServiceImpl implements LizardService{

    private final LizardRepository lizardRepository;
    private final MemberRepository memberRepository;


    @Override
    public void saveLizard(Long memberId, LizardReqDTO lizardReqDTO) {

        Lizard lizard = Lizard.fromReqDto(lizardReqDTO);
        Member member = memberRepository.findById(memberId).orElse(null);
        lizard.setMember(member);

        lizardRepository.save(lizard);
    }

    @Override
    public List<LizardResDTO> getLizards(Long memberId) {

        Member member = memberRepository.findById(memberId).orElse(null);

        List<Lizard> lizardAllList = lizardRepository.findAllByMember(member);

        return lizardAllList.stream()
                .map(lizard -> LizardResDTO.builder()
                        .lizardId(lizard.getId())
                        .lizardName(lizard.getLizardName())
                        .adoptDate(lizard.getAdoptDate())
                        .birthDate(lizard.getBirthDate())
                        .species(lizard.getSpecies())
                        .morph(lizard.getMorph())
                        .optTemperature(lizard.getOptTemperature())
                        .optHumidity(lizard.getOptHumidity())
                        .hausNumber(lizard.getHausNumber())
                        .build())
                .collect(Collectors.toList());
    }
}
