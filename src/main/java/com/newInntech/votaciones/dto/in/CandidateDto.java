package com.newInntech.votaciones.dto.in;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDto {
    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    private String party;
}
