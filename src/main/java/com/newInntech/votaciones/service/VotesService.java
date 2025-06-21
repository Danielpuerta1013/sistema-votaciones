package com.newInntech.votaciones.service;

import com.newInntech.votaciones.dto.out.ResponseDto;
import com.newInntech.votaciones.dto.out.StatisticsResponse;
import com.newInntech.votaciones.dto.out.VotesResponse;

import java.util.List;

public interface VotesService {
    ResponseDto createVotes(Long voterId, Long candidateId);
    List<VotesResponse> getAllVotes();

    StatisticsResponse getStatistics();

}
