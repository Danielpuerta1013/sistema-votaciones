package com.newInntech.votaciones.serviceImpl;

import com.newInntech.votaciones.dto.in.CandidateDto;
import com.newInntech.votaciones.dto.out.CandidateResponse;
import com.newInntech.votaciones.dto.out.ResponseDto;
import com.newInntech.votaciones.exception.DuplicateResourceException;
import com.newInntech.votaciones.exception.ResourceNotFoundException;
import com.newInntech.votaciones.helper.Entityvalidator;
import com.newInntech.votaciones.helper.UserRolValidator;
import com.newInntech.votaciones.model.Candidate;
import com.newInntech.votaciones.repository.CandidateRepository;
import com.newInntech.votaciones.service.CandidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final UserRolValidator userRolValidator;
    private final Entityvalidator entityvalidator;

    @Override
    public ResponseDto createCandidate(CandidateDto candidateDto) {
        entityvalidator.validateCandidate(candidateDto.getName());
        userRolValidator.validateNotVoter(candidateDto.getName());

        var candidate= Candidate.builder()
                .name(candidateDto.getName())
                .party(candidateDto.getParty())
                .votes(0L)
                .build();
        candidateRepository.save(candidate);
        return new ResponseDto("El candidato se ha creado correctamente");
    }

    @Override
    public CandidateResponse getCandidateById(Long id) {
        var candidate= entityvalidator.validateCandidateId(id);
        return CandidateResponse.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .party(candidate.getParty())
                .votes(candidate.getVotes())
                .build();
    }


    @Override
    public List<CandidateResponse> getAllCandidates() {
        var candidates=candidateRepository.findAll();
        return candidates.stream()
                .map(voter-> CandidateResponse.builder()
                        .id(voter.getId())
                        .name(voter.getName())
                        .party(voter.getParty())
                        .votes(voter.getVotes())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDto deleteCandidateById(Long id) {
        var candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El candidato con id " +id+" no existe"));
        candidateRepository.delete(candidate);
        return new ResponseDto("El candidato se ha eliminado correctamente");
    }

    @Override
    public Page<CandidateResponse> getCandidatesPaginated(Pageable pageable, String name, String party) {
        Page<Candidate> candidates;
        if (name != null && party != null) {
            candidates= candidateRepository.findByNameContainingIgnoreCaseAndPartyContainingIgnoreCase(name, party, pageable);
        }else if (name != null) {
            candidates = candidateRepository.findByNameContainingIgnoreCase(name, pageable);

        }else if (party != null) {
            candidates = candidateRepository.findByPartyContainingIgnoreCase(party, pageable);
        } else {
            candidates = candidateRepository.findAll(pageable);
        }
        return candidates.map(candidate -> CandidateResponse.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .party(candidate.getParty())
                .votes(candidate.getVotes())
                .build());
    }
}
