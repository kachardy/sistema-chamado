package com.example.base.dto.response;

import com.example.base.model.Categoria;
import com.example.base.model.Prioridade;
import com.example.base.model.Status;

public record ChamadoRespDto(
        Long id,
        String titulo,
        String descricao,
        Prioridade prioridade,
        Status status,
        UsuarioRespDto usuarioRespDto,
        Categoria categoria) {}
