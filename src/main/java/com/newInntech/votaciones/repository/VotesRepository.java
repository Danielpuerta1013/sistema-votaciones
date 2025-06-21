package com.newInntech.votaciones.repository;

import com.newInntech.votaciones.model.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotesRepository extends JpaRepository<Votes, Long> {
}
