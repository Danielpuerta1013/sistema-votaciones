package com.newInntech.votaciones.helper;

import com.newInntech.votaciones.exception.DuplicateResourceException;
import com.newInntech.votaciones.repository.CandidateRepository;
import com.newInntech.votaciones.repository.VoterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRolValidator {
    private final VoterRepository voterRepository;
    private final CandidateRepository candidateRepository;

    public void validateNotVoter(String name){
        if(voterRepository.findByName(name).isPresent()){
            throw new DuplicateResourceException("Este usuario ya esta registrado como votante");
        }
    }

    public void validateNotCandidate(String name){
        if(candidateRepository.findByName(name).isPresent()){
            throw new DuplicateResourceException("Este usuario ya esta registrado como candidato");
        }
    }
}
