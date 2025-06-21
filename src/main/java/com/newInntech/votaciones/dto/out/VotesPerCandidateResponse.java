package com.newInntech.votaciones.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotesPerCandidateResponse {
    private String name;
    private Long votes;
}
