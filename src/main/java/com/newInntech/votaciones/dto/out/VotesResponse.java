package com.newInntech.votaciones.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotesResponse {
    private String voterName;
    private String candidateName;
}
