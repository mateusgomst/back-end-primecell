package com.prime_cell.back_end.exceptions;

// Para erros de processamento de dados
public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message) {
        super(message);
    }
}