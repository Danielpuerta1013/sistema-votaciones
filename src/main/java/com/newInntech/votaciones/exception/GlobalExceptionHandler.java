package com.newInntech.votaciones.exception;

import com.newInntech.votaciones.dto.out.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ResponseDto> handleDuplicate(DuplicateResourceException e){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ResponseDto(e.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseDto> handleResourceNotFound(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseDto(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleMethodArgumentNotValid(MethodArgumentNotValidException e){
        String errorMessage = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDto(errorMessage));
    }

    @ExceptionHandler(AlreadyVotedException.class)
    public ResponseEntity<ResponseDto> handleAlreadyVoted(AlreadyVotedException e){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ResponseDto(e.getMessage()));
    }
}
