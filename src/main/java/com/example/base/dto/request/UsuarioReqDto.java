package com.example.base.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioReqDto(
        @NotBlank(message = "Nome é obrigatório")
        @Size(message = "O tamanho do nome deve ser a partir de 8 caracteres e no máximo 100", min = 3, max = 100)
        String nome,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Formato de email inválido")
        String email,

        @NotBlank(message = "O campo senha é obrigatório")
        @Size(message = "O tamanho da senha deve ser a partir de 8 caracteres", min = 8)
        String senha,

        @NotBlank
        @Size(message = "O tamanho da senha deve ser a partir de 8 caracteres",min = 8)
        String confirmacaoSenha) {
}
