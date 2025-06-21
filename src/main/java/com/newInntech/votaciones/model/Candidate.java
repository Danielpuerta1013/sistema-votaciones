package com.newInntech.votaciones.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "candidate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Column(name = "candidate_name", nullable = false)
    private String name;

    @Column(name="party", nullable = true)
    private String party;

    @Column(name = "votes")
    private Long votes=0L;
}
