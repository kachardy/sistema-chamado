package com.example.base.controller;

import com.example.base.dto.request.UsuarioReqDto;
import com.example.base.dto.response.UsuarioRespDto;
import com.example.base.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/sistemaChamado")
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping("/admins")
    public ResponseEntity<UsuarioRespDto> cadastrarUsuario(@RequestBody @Valid UsuarioReqDto usuarioReqDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarAdministrador(usuarioReqDto));
    }

    @GetMapping("/admins")
    public ResponseEntity<Page<UsuarioRespDto>> listarUsuarios(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarAdministradores(pageable));
    }

}
