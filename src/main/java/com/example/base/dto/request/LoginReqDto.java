package com.example.base.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public record LoginReqDto (

    @NotBlank(message = "Email deve ser informado")
    @Email(message = "Email inválido")
    String email,

    @NotBlank(message = "A senha deve ser informada")
    @Length(min = 8, message = "A senha deve conter pelo menos 8 caracteres")
    String senha
) {};
