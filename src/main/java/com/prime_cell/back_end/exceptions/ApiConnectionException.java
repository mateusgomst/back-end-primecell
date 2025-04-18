package com.prime_cell.back_end.exceptions;

// Para erros de conexão ou comunicação com APIs externas
public class ApiConnectionException extends RuntimeException {
    public ApiConnectionException(String message) {
        super(message);
    }
}