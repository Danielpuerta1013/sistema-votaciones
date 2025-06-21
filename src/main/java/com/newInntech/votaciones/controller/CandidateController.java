package com.newInntech.votaciones.controller;

import com.newInntech.votaciones.dto.in.CandidateDto;
import com.newInntech.votaciones.dto.out.CandidateResponse;
import com.newInntech.votaciones.dto.out.ResponseDto;
import com.newInntech.votaciones.service.CandidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidate")
@RequiredArgsConstructor
@Tag(name = "Candidate", description = "controlador para gestionar candidatos")
public class CandidateController {
    private final CandidateService candidateService;

    @Operation(
            summary = "Registrar un nuevo candidato",
            description = "Crea un nuevo candidato en el sistema. Verifica que no esté registrado como votante ni duplicado por nombre. "
                    + "Si el candidato ya existe o el nombre está registrado como votante, se lanza una excepción."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Candidato creado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "409", description = "Conflicto: candidato duplicado o registrado como votante",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en el cuerpo de la solicitud",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public ResponseEntity<ResponseDto> createCandidate(@Valid @RequestBody CandidateDto candidateDto){
        ResponseDto responseDto = candidateService.createCandidate(candidateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Operation(
            summary = "Obtener un candidato por su ID",
            description = "Recupera la información de un candidato registrado a partir de su identificador único. "
                    + "Si no existe un candidato con ese ID, se lanza una excepción personalizada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Candidato encontrado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CandidateResponse.class))),
            @ApiResponse(responseCode = "404", description = "Candidato no encontrado",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping
    public ResponseEntity<CandidateResponse> getCandidateById(@RequestParam Long id){
        CandidateResponse candidateResponse = candidateService.getCandidateById(id);
        return ResponseEntity.ok(candidateResponse);
    }

    @Operation(
            summary = "Listar todos los candidatos",
            description = "Obtiene una lista con todos los candidatos registrados en el sistema, incluyendo su nombre, partido y número de votos."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de candidatos obtenida exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CandidateResponse.class)))
    })
    @GetMapping("/all")
    public ResponseEntity<List<CandidateResponse>> getAllVoters(){
        List<CandidateResponse> candidateResponses = candidateService.getAllCandidates();
        return ResponseEntity.ok(candidateResponses);
    }

    @Operation(
            summary = "Eliminar candidato por ID",
            description = "Elimina un candidato del sistema si existe. El ID del candidato se pasa como parámetro de la solicitud."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Candidato eliminado correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "El candidato con el ID especificado no fue encontrado",
                    content = @Content)
    })
    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteVoterById(@RequestParam Long id){
        ResponseDto responseDto = candidateService.deleteCandidateById(id);
        return ResponseEntity.ok(responseDto);
    }
}
