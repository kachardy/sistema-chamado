package com.example.base.dto.request;

import com.example.base.model.Prioridade;
import com.example.base.model.Status;
import com.example.base.model.Usuario;

public record ChamadoReqDto(String titulo, String descricao, Prioridade prioridade, Status status, Usuario usuario) {
}
