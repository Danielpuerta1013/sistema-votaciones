package com.newInntech.votaciones.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "voter")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato de email invalido")
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "has_voted", nullable = false)
    private Boolean hasVoted=false;
}
