package com.newInntech.votaciones.controller;

import com.newInntech.votaciones.dto.in.CandidateDto;
import com.newInntech.votaciones.dto.out.CandidateResponse;
import com.newInntech.votaciones.dto.out.ResponseDto;
import com.newInntech.votaciones.service.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidate")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;

    @PostMapping
    public ResponseEntity<ResponseDto> createCandidate(@Valid @RequestBody CandidateDto candidateDto){
        ResponseDto responseDto = candidateService.createCandidate(candidateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<CandidateResponse> getCandidateById(@RequestParam Long id){
        CandidateResponse candidateResponse = candidateService.getCandidateById(id);
        return ResponseEntity.ok(candidateResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CandidateResponse>> getAllVoters(){
        List<CandidateResponse> candidateResponses = candidateService.getAllCandidates();
        return ResponseEntity.ok(candidateResponses);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteVoterById(@RequestParam Long id){
        ResponseDto responseDto = candidateService.deleteCandidateById(id);
        return ResponseEntity.ok(responseDto);
    }
}
