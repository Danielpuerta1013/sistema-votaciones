package com.newInntech.votaciones.service;

import com.newInntech.votaciones.dto.in.CandidateDto;
import com.newInntech.votaciones.dto.out.CandidateResponse;
import com.newInntech.votaciones.dto.out.ResponseDto;

import java.util.List;

public interface CandidateService {
    ResponseDto createCandidate(CandidateDto candidateDto);
    CandidateResponse getCandidateById(Long id);
    List<CandidateResponse> getAllCandidates();
    ResponseDto deleteCandidateById(Long id);

}
