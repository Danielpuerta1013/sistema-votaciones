package com.newInntech.votaciones.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoterResponse {
    private Long id;
    private String name;
    private String email;
    private Boolean hasVoted;
}
