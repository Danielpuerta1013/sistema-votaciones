package com.newInntech.votaciones.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PercentagePerCandidateResponse {
    private String name;
    private Double percentage;
}
