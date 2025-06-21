package com.newInntech.votaciones.repository;

import com.newInntech.votaciones.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {
    Optional<Candidate> findByName(String name);
    boolean existsById(Long id);
}
