package com.prime_cell.back_end.models;

import com.prime_cell.back_end.exceptions.InvalidFormatEnum;

import java.util.Arrays;

public enum ProductType {
    ELETRONICOS,
    FONES,
    CARREGADORES,
    CASE,
    BATERIAS,
    ACESSORIOS,
    OUTROS;

    // Converte a string para o enum correspondente
    public static ProductType fromString(String value) {
        return Arrays.stream(ProductType.values())
                .filter(type -> type.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new InvalidFormatEnum("Valor inválido para ProductType. Valores permitidos: ELETRONICOS, FONES, CARREGADORES, CASE, BATERIAS, ACESSORIOS, OUTROS"));
    }
}