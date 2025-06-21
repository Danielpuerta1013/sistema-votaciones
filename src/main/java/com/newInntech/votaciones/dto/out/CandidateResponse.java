package com.newInntech.votaciones.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateResponse {
    private Long id;
    private String name;
    private String party;
    private Long votes;
}
