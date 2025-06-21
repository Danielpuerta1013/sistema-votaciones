package com.newInntech.votaciones.serviceImpl;

import com.newInntech.votaciones.dto.out.*;
import com.newInntech.votaciones.exception.AlreadyVotedException;
import com.newInntech.votaciones.exception.ResourceNotFoundException;
import com.newInntech.votaciones.model.Votes;
import com.newInntech.votaciones.repository.CandidateRepository;
import com.newInntech.votaciones.repository.VoterRepository;
import com.newInntech.votaciones.repository.VotesRepository;
import com.newInntech.votaciones.service.VotesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Votes", description = "Servicio para gestionar votos")
public class VotesServiceImpl implements VotesService {
    private final VotesRepository votesRepository;
    private final VoterRepository voterRepository;
    private final CandidateRepository candidateRepository;

    @Override
    public ResponseDto createVotes(Long voterId, Long candidateId) {
        var voter = voterRepository.findById(voterId)
                .orElseThrow(() -> new ResourceNotFoundException("El votante con id " +voterId+" no existe"));
        var candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new ResourceNotFoundException("El candidato con id " +candidateId+" no existe"));
        if(voter.getHasVoted()){
            throw new AlreadyVotedException("Ya existe un voto para este votante");
        }
        var votes = Votes.builder()
                .voter(voter)
                .candidate(candidate)
                .build();
        votesRepository.save(votes);

        voter.setHasVoted(true);
        voterRepository.save(voter);
        candidate.setVotes(candidate.getVotes()+1);
        candidateRepository.save(candidate);
        return new ResponseDto("El voto se ha creado correctamente");
    }

    @Override
    public List<VotesResponse> getAllVotes() {
        var votes=votesRepository.findAll();
        return votes.stream()
                .map(vote-> VotesResponse.builder()
                        .voterName(vote.getVoter().getName())
                        .candidateName(vote.getCandidate().getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public StatisticsResponse getStatistics() {
        var candidates=candidateRepository.findAll();
        long totalVotes=voterRepository.countByHasVotedTrue();

        List<VotesPerCandidateResponse> votesPerCandidate=candidates.stream()
                .map(candidate -> VotesPerCandidateResponse.builder()
                        .name(candidate.getName())
                        .votes(candidate.getVotes())
                        .build())
                .toList();
        List<PercentagePerCandidateResponse> percentagePerCandidate=candidates.stream()
                .map(candidate -> {
                    double percentage= (totalVotes==0)?0.0:
                            ((double) candidate.getVotes()/totalVotes)*100;
                    return PercentagePerCandidateResponse.builder()
                            .name(candidate.getName())
                            .percentage(percentage)
                            .build();
                }).toList();
        return StatisticsResponse.builder()
                .votesPerCandidate(votesPerCandidate)
                .percentagePerCandidate(percentagePerCandidate)
                .totalVotes(totalVotes)
                .build();
    }
}
