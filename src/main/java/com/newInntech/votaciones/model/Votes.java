package com.newInntech.votaciones.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vote")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Votes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name="voter_id", nullable = false)
    private Voter voter;

    @ManyToOne(optional = false)
    @JoinColumn(name="candidate_id", nullable = false)
    private Candidate candidate;
}
