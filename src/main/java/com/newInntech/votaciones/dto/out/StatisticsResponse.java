package com.newInntech.votaciones.dto.out;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticsResponse {
    private List<VotesPerCandidateResponse> votesPerCandidate;
    private List<PercentagePerCandidateResponse> percentagePerCandidate;
    private Long totalVotes;
}
