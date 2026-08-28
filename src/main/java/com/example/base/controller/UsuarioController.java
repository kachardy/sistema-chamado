package com.example.base.controller;

import com.example.base.dto.request.UsuarioReqDto;
import com.example.base.dto.response.UsuarioRespDto;
import com.example.base.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/sistemaChamado")
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping("/admins")
    public ResponseEntity<UsuarioRespDto> cadastrarUsuario(@RequestBody UsuarioReqDto usuarioReqDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarAdministrador(usuarioReqDto));
    }

    @GetMapping("/admins")
    public ResponseEntity<List<UsuarioRespDto>> listarUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarAdministradores());
    }

}
