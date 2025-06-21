package com.newInntech.votaciones.controller;

import com.newInntech.votaciones.dto.out.ResponseDto;
import com.newInntech.votaciones.dto.out.StatisticsResponse;
import com.newInntech.votaciones.dto.out.VotesResponse;
import com.newInntech.votaciones.service.VotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/votes")
@RequiredArgsConstructor
public class VotesController {

    private final VotesService votesService;

    @PostMapping
    public ResponseEntity<ResponseDto>createVotes(@RequestParam Long voterId, @RequestParam Long candidateId){
        ResponseDto responseDto = votesService.createVotes(voterId, candidateId);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<VotesResponse>> getAllVotes(){
        List<VotesResponse> votesResponses = votesService.getAllVotes();
        return ResponseEntity.ok(votesResponses);
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponse> getStatistics(){
        return ResponseEntity.ok(votesService.getStatistics());
    }
}
