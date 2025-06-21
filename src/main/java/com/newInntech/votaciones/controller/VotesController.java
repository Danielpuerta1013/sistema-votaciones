package com.newInntech.votaciones.controller;

import com.newInntech.votaciones.dto.out.ResponseDto;
import com.newInntech.votaciones.dto.out.StatisticsResponse;
import com.newInntech.votaciones.dto.out.VotesResponse;
import com.newInntech.votaciones.service.VotesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/votes")
@RequiredArgsConstructor
@Tag(name = "Votes", description = "Controlador para gestionar votos")
public class VotesController {

    private final VotesService votesService;

    @Operation(
            summary = "Registrar un voto",
            description = """
        Registra un nuevo voto en el sistema. Se debe proporcionar el ID del votante y el ID del candidato. 
        El votante debe existir y no haber votado previamente. 
        Al registrarse el voto:
        - Se crea una entrada en la tabla `votes`.
        - Se marca al votante como `hasVoted = true`.
        - Se incrementa el contador de votos del candidato.
        """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Voto registrado correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Votante o candidato no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "El votante ya ha emitido un voto",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)))
    })
    @PostMapping
    public ResponseEntity<ResponseDto>createVotes(@RequestParam Long voterId, @RequestParam Long candidateId){
        ResponseDto responseDto = votesService.createVotes(voterId, candidateId);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Operation(
            summary = "Obtener todos los votos",
            description = """
        Retorna una lista con todos los votos registrados en el sistema.
        Cada voto incluye el nombre del votante y el nombre del candidato por el que votó.
        Esta operación permite tener visibilidad completa del proceso electoral.
        """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de votos obtenido correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = VotesResponse.class)))
    })
    @GetMapping
    public ResponseEntity<List<VotesResponse>> getAllVotes(){
        List<VotesResponse> votesResponses = votesService.getAllVotes();
        return ResponseEntity.ok(votesResponses);
    }

    @Operation(
            summary = "Obtener estadísticas de votación",
            description = """
        Devuelve estadísticas generales del proceso electoral actual:
        
        - Total de votos emitidos.
        - Total de votos por candidato.
        - Porcentaje de votos por candidato respecto al total.

        Esta información es útil para reportes, visualización en dashboards o cierre de votaciones.
        """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Estadísticas generadas correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = StatisticsResponse.class))
            )
    })
    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponse> getStatistics(){
        return ResponseEntity.ok(votesService.getStatistics());
    }
}
