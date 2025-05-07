package com.prime_cell.back_end.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ApiConnectionException.class)
    public ResponseEntity<String> handleApiConnectionException(ApiConnectionException ex) {
        logger.error("Erro de conexão com API: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Erro de conexão: " + ex.getMessage());
    }

    @ExceptionHandler(InsufficientDataException.class)
    public ResponseEntity<String> handleInsufficientDataException(InsufficientDataException ex) {
        logger.error("Dados insuficientes: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(DataProcessingException.class)
    public ResponseEntity<String> handleDataProcessingException(DataProcessingException ex) {
        logger.error("Erro no processamento: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro no processamento: " + ex.getMessage());
    }

    @ExceptionHandler(AdminNotFoundException.class)
    public ResponseEntity<String> handleAdminNotFoundException(AdminNotFoundException ex) {
        logger.error("Admin não encontrado: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Admin não encontrado: " + ex.getMessage());
    }

    @ExceptionHandler(AdminAlreadyExistsException.class)
    public ResponseEntity<String> handleAdminAlreadyExistsException(AdminAlreadyExistsException ex) {
        logger.error("Admin já existe: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        logger.error("Erro inesperado: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno do servidor");
    }

    @ExceptionHandler(InvalidFormatEnumException.class)
    public ResponseEntity<String> handleInvalidFormatEnum(InvalidFormatEnumException ex) {
        logger.error("Erro de formato inválido: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Erro: " + ex.getMessage());
    }
}