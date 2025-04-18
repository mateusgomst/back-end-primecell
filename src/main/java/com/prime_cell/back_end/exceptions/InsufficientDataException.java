package com.prime_cell.back_end.exceptions;

// Para dados inválidos ou insuficientes (já existe)
public class InsufficientDataException extends RuntimeException {
    public InsufficientDataException(String message) {
        super(message);
    }
}