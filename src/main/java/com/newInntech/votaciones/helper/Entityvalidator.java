package com.newInntech.votaciones.helper;

import com.newInntech.votaciones.exception.DuplicateResourceException;
import com.newInntech.votaciones.exception.ResourceNotFoundException;
import com.newInntech.votaciones.model.Candidate;
import com.newInntech.votaciones.model.Voter;
import com.newInntech.votaciones.repository.CandidateRepository;
import com.newInntech.votaciones.repository.VoterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Entityvalidator {
    private final VoterRepository voterRepository;
    private final CandidateRepository candidateRepository;

    public void validateVoter(String name){
        if(voterRepository.findByName(name).isPresent()){
            throw new DuplicateResourceException("Ya existe un votante con ese nombre");
        }
    }

    public Voter validateVoterId(Long id){
        return voterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El votante con id " +id+" no existe"));
    }

    public void validateVoterEmail(String email){
        if(voterRepository.findByEmail(email).isPresent()){
            throw new DuplicateResourceException("Ya existe un votante con ese email");
        }
    }

    public void validateCandidate(String name){
        if(candidateRepository.findByName(name).isPresent()){
            throw new DuplicateResourceException("Ya existe un candidato con ese nombre");
        }
    }

    public  Candidate validateCandidateId(Long id){
        return candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El candidato con id " +id+" no existe"));
    }




}
