package com.example.base.dto.request;

import com.example.base.model.Categoria;
import com.example.base.model.Prioridade;
import com.example.base.model.Status;
import com.example.base.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ChamadoReqDto(

        @NotBlank(message = "O campo título é obrigatório")
        @Size(message = "O tamanho do título deve ser a partir de 5 caracteres e no máximo 100", min = 5, max = 100)
        String titulo,

        @NotBlank(message = "O campo descrição é obrigatório")
        @Size(message = "O tamanho da descrição deve ser a partir de 10 caracteres e no máximo 1000", min = 10, max = 1000)
        String descricao,

        @NotNull(message = "O campo prioridade é obrigatório")
        Prioridade prioridade,

        @NotNull(message = "O campo categoria é obrigatório")
        Categoria categoria) {}
