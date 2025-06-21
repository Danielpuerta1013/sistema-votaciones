package com.newInntech.votaciones.exception;

public class AlreadyVotedException extends RuntimeException{
    public AlreadyVotedException(String message) {
        super(message);
    }
}
