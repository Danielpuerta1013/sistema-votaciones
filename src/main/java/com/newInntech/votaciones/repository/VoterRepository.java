package com.newInntech.votaciones.repository;

import com.newInntech.votaciones.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {
    Optional<Voter> findByName(String name);
    Optional<Voter> findByEmail(String email);
    boolean existsById(Long id);

    long countByHasVotedTrue();

}
