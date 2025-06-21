package com.newInntech.votaciones.service;

import com.newInntech.votaciones.dto.in.CandidateDto;
import com.newInntech.votaciones.dto.out.CandidateResponse;
import com.newInntech.votaciones.dto.out.ResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CandidateService {
    ResponseDto createCandidate(CandidateDto candidateDto);
    CandidateResponse getCandidateById(Long id);
    List<CandidateResponse> getAllCandidates();
    ResponseDto deleteCandidateById(Long id);
    Page<CandidateResponse> getCandidatesPaginated(Pageable pageable, String name, String party);

}
