package com.example.base.core.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ErroPadrao(
        LocalDateTime timestamp,
        Integer status,
        String mensagem,
        Map<String, String> detalhes
) {
    // Construtor para erros simples
    public ErroPadrao(Integer status, String mensagem) {
        this(LocalDateTime.now(), status, mensagem, null);
    }

    // Construtor para erros de validação
    public ErroPadrao(Integer status, String mensagem, Map<String, String> detalhes) {
        this(LocalDateTime.now(), status, mensagem, detalhes);
    }
}