package com.cx_project.kiiun.domain.lizard.service;

import com.cx_project.kiiun.domain.growth.entity.Growth;
import com.cx_project.kiiun.domain.growth.repository.GrowthRepository;
import com.cx_project.kiiun.domain.lizard.dto.request.LizardReqDTO;
import com.cx_project.kiiun.domain.lizard.dto.response.LizardMatingResDTO;
import com.cx_project.kiiun.domain.lizard.dto.response.LizardResDTO;
import com.cx_project.kiiun.domain.lizard.entity.Lizard;
import com.cx_project.kiiun.domain.lizard.repository.LizardRepository;
import com.cx_project.kiiun.domain.member.entity.Member;
import com.cx_project.kiiun.domain.member.repository.MemberRepository;
import com.cx_project.kiiun.global.exception.CustomException;
import com.cx_project.kiiun.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LizardServiceImpl implements LizardService{

    private final LizardRepository lizardRepository;
    private final MemberRepository memberRepository;
    private final GrowthRepository growthRepository;


    @Override
    public void saveLizard(Long memberId, LizardReqDTO lizardReqDTO) {

        Lizard lizard = Lizard.fromReqDto(lizardReqDTO);
        Member member = memberRepository.findById(memberId).orElse(null);
        lizard.setMember(member);

        lizardRepository.save(lizard);

        //몸무게 저장
        Growth growth = Growth.builder()
                .weight(lizardReqDTO.getCurrentWeight())
                .lizard(lizard)
                .build();

        growthRepository.save(growth);

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
                        .currentWeight(lizard.getCurrentWeight())
                        .species(lizard.getSpecies())
                        .morph(lizard.getMorph())
                        .optTemperature(lizard.getOptTemperature())
                        .optHumidity(lizard.getOptHumidity())
                        .hausNumber(lizard.getHausNumber())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<LizardMatingResDTO> getMatings(Long memberId) {

        Member curMember = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(ErrorCode.NO_SUCH_MEMBER) {
            @Override
            public ErrorCode getErrorCode() {
                return super.getErrorCode();
            }
        });

        Double memberLongitude = curMember.getLongitude();
        Double memberLatitude = curMember.getLatitude();

        List<Member> otherMembers = memberRepository.findAllByIdNot(memberId);

        List<Member> nearestMembers = otherMembers.stream()
                .sorted(Comparator.comparingDouble(member -> calculateDistance(memberLatitude, memberLongitude, member.getLatitude(), member.getLongitude())))
                .limit(5)
                .collect(Collectors.toList());


        return nearestMembers.stream()
                .flatMap(member -> lizardRepository.findAllByMember(member).stream()
                        .map(lizard -> new LizardMatingResDTO(
                                member.getNickname(),
                                member.getLocation(),
                                lizard.getLizardName(),
                                lizard.getAdoptDate(),
                                lizard.getBirthDate(),
                                lizard.getCurrentWeight(),
                                lizard.getSpecies(),
                                lizard.getMorph()
                        )))
                .collect(Collectors.toList());
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS_KM = 6371;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        //Haversine 공식 적용
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
}
