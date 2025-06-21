package com.newInntech.votaciones.service;

import com.newInntech.votaciones.dto.in.VoterDto;
import com.newInntech.votaciones.dto.out.ResponseDto;
import com.newInntech.votaciones.dto.out.VoterResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VoterService {
    ResponseDto createVoter(VoterDto voterDto);
    VoterResponse getVoterById(Long id);
    List<VoterResponse> getAllVoters();
    ResponseDto deleteVoterById(Long id);

    Page<VoterResponse> getVotersPaginated(Pageable pageable, String name, String email);

}
