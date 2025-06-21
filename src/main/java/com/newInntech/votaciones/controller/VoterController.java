package com.newInntech.votaciones.controller;

import com.newInntech.votaciones.dto.in.VoterDto;
import com.newInntech.votaciones.dto.out.ResponseDto;
import com.newInntech.votaciones.dto.out.VoterResponse;
import com.newInntech.votaciones.service.VoterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voter")
@RequiredArgsConstructor
public class VoterController {
    private final VoterService voterService;

    @PostMapping
    public ResponseEntity<ResponseDto> createVoter(@Valid @RequestBody VoterDto voterDto){
        ResponseDto responseDto = voterService.createVoter(voterDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<VoterResponse> getVoterById(@RequestParam Long id){
        VoterResponse voterResponse = voterService.getVoterById(id);
        return ResponseEntity.ok(voterResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<VoterResponse>> getAllVoters(){
        List<VoterResponse> voterResponses = voterService.getAllVoters();
        return ResponseEntity.ok(voterResponses);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteVoterById(@RequestParam Long id){
        ResponseDto responseDto = voterService.deleteVoterById(id);
        return ResponseEntity.ok(responseDto);
    }
}
