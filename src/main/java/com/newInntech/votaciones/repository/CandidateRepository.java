package com.newInntech.votaciones.repository;

import com.newInntech.votaciones.model.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {
    Optional<Candidate> findByName(String name);
    boolean existsById(Long id);
    Page<Candidate> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Candidate> findByPartyContainingIgnoreCase(String party, Pageable pageable);
    Page<Candidate> findByNameContainingIgnoreCaseAndPartyContainingIgnoreCase(String name, String party, Pageable pageable);
}
