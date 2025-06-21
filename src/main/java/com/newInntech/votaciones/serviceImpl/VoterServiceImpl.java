package com.newInntech.votaciones.serviceImpl;

import com.newInntech.votaciones.exception.DuplicateResourceException;
import com.newInntech.votaciones.dto.in.VoterDto;
import com.newInntech.votaciones.dto.out.ResponseDto;
import com.newInntech.votaciones.dto.out.VoterResponse;
import com.newInntech.votaciones.exception.ResourceNotFoundException;
import com.newInntech.votaciones.helper.Entityvalidator;
import com.newInntech.votaciones.helper.UserRolValidator;
import com.newInntech.votaciones.model.Voter;
import com.newInntech.votaciones.repository.CandidateRepository;
import com.newInntech.votaciones.repository.VoterRepository;
import com.newInntech.votaciones.service.VoterService;
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
public class VoterServiceImpl implements VoterService {
    private final VoterRepository voterRepository;
    private final UserRolValidator userRolValidator;
    private final Entityvalidator entityvalidator;
    @Override
    public ResponseDto createVoter(VoterDto voterDto) {
        entityvalidator.validateVoter(voterDto.getName());
        entityvalidator.validateVoterEmail(voterDto.getEmail());
        userRolValidator.validateNotCandidate(voterDto.getName());

        var voter= Voter.builder()
                .name(voterDto.getName())
                .email(voterDto.getEmail())
                .hasVoted(false)
                .build();
        voterRepository.save(voter);
        return new ResponseDto("El votante se ha creado correctamente");
    }

    @Override
    public VoterResponse getVoterById(Long id) {
        var voter= entityvalidator.validateVoterId(id);
        return VoterResponse.builder()
                .id(voter.getId())
                .name(voter.getName())
                .email(voter.getEmail())
                .hasVoted(voter.getHasVoted())
                .build();
    }

    @Override
    public List<VoterResponse> getAllVoters() {
        var voters=voterRepository.findAll();
        return voters.stream()
                .map(voter-> VoterResponse.builder()
                        .id(voter.getId())
                        .name(voter.getName())
                        .email(voter.getEmail())
                        .hasVoted(voter.getHasVoted())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDto deleteVoterById(Long id) {
        var voter = voterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El votante con id " +id+" no existe"));
        voterRepository.delete(voter);
        return new ResponseDto("El votante se ha eliminado correctamente");
    }

    @Override
    public Page<VoterResponse> getVotersPaginated(Pageable pageable, String name, String email) {
        Page<Voter> voters;
        if (name != null && email != null) {
            voters= voterRepository.findByNameContainingIgnoreCaseAndEmailContainingIgnoreCase(name, email, pageable);
        } else if (name != null) {
            voters = voterRepository.findByNameContainingIgnoreCase(name, pageable);

        }else if (email != null) {
            voters = voterRepository.findByEmailContainingIgnoreCase(email, pageable);
        } else {
            voters = voterRepository.findAll(pageable);
        }
        return voters.map(voter -> VoterResponse.builder()
                .id(voter.getId())
                .name(voter.getName())
                .email(voter.getEmail())
                .hasVoted(voter.getHasVoted())
                .build());
    }


}
