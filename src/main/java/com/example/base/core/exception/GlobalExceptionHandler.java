package com.example.base.core.exception;

import com.example.base.exception.RecursoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroPadrao> tratarRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErroPadrao(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadrao> tratarErrosDeValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> errosCampos = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errosCampos.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErroPadrao(HttpStatus.BAD_REQUEST.value(), "Erro de validação nos dados enviados", errosCampos));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroPadrao> tratarRegraDeNegocio(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErroPadrao(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroPadrao> tratarErroDeDesserializacao(HttpMessageNotReadableException ex) {
        String mensagem = "Erro na leitura do JSON. Verifique se os dados enviados (como Prioridade ou Categoria) estão corretos e correspondem aos valores aceitos pelo sistema.";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErroPadrao(HttpStatus.BAD_REQUEST.value(), mensagem));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErroPadrao> tratarAcessoNegado(org.springframework.security.access.AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErroPadrao(HttpStatus.FORBIDDEN.value(), ex.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErroPadrao> tratarErroDeLogin(org.springframework.security.core.AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErroPadrao(HttpStatus.UNAUTHORIZED.value(), "E-mail ou senha incorretos."));
    }
}