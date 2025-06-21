package com.newInntech.votaciones.controller;

import com.newInntech.votaciones.dto.in.VoterDto;
import com.newInntech.votaciones.dto.out.ResponseDto;
import com.newInntech.votaciones.dto.out.VoterResponse;
import com.newInntech.votaciones.service.VoterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voter")
@RequiredArgsConstructor
@Tag(name = "Voter", description = "controlador para gestionar votantes")
public class VoterController {
    private final VoterService voterService;

    @Operation(
            summary = "Registrar nuevo votante",
            description = "Registra un nuevo votante en el sistema. Valida que no esté registrado previamente como votante ni como candidato."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Votante creado exitosamente",
                    content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "409", description = "El votante ya existe o está registrado como candidato",
                    content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud",
                    content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @PostMapping
    public ResponseEntity<ResponseDto> createVoter(@Valid @RequestBody VoterDto voterDto){
        ResponseDto responseDto = voterService.createVoter(voterDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Operation(
            summary = "Obtener votante por ID",
            description = "Retorna la información de un votante específico a partir de su ID. Lanza error si el ID no existe en el sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Votante encontrado exitosamente",
                    content = @Content(schema = @Schema(implementation = VoterResponse.class))),
            @ApiResponse(responseCode = "404", description = "No se encontró el votante con el ID especificado",
                    content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<VoterResponse> getVoterById(@RequestParam Long id){
        VoterResponse voterResponse = voterService.getVoterById(id);
        return ResponseEntity.ok(voterResponse);
    }

    @Operation(
            summary = "Listar todos los votantes",
            description = "Obtiene una lista completa de todos los votantes registrados en el sistema. Esta operación no requiere parámetros."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de votantes obtenida exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = VoterResponse.class)))
    })
    @GetMapping("/all")
    public ResponseEntity<List<VoterResponse>> getAllVoters(){
        List<VoterResponse> voterResponses = voterService.getAllVoters();
        return ResponseEntity.ok(voterResponses);
    }

    @Operation(
            summary = "Eliminar votante por ID",
            description = "Elimina un votante registrado en el sistema mediante su identificador único. "
                    + "Si el votante no existe, se devuelve un error 404."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Votante eliminado correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Votante no encontrado",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteVoterById(@RequestParam Long id){
        ResponseDto responseDto = voterService.deleteVoterById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/paginated")
    @Operation(summary = "Listar votantes con paginación y filtrado opcional por nombre y email")
    public ResponseEntity<Page<VoterResponse>> getVotersPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<VoterResponse> response = voterService.getVotersPaginated(pageable, name, email);
        return ResponseEntity.ok(response);

    }
}
