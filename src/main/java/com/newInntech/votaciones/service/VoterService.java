package com.newInntech.votaciones.service;

import com.newInntech.votaciones.dto.in.VoterDto;
import com.newInntech.votaciones.dto.out.ResponseDto;
import com.newInntech.votaciones.dto.out.VoterResponse;

import java.util.List;

public interface VoterService {
    ResponseDto createVoter(VoterDto voterDto);
    VoterResponse getVoterById(Long id);
    List<VoterResponse> getAllVoters();
    ResponseDto deleteVoterById(Long id);

}
