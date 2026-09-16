package com.example.base.controller;

import com.example.base.dto.request.LoginReqDto;
import com.example.base.dto.request.UsuarioReqDto;
import com.example.base.dto.response.UsuarioRespDto;
import com.example.base.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth")
@AllArgsConstructor
public class AuthController {

    private final com.example.base.service.AuthService authService;
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public org.springframework.http.ResponseEntity<String> login(@RequestBody @jakarta.validation.Valid LoginReqDto dto) {
        String token = authService.login(dto);
        return org.springframework.http.ResponseEntity.ok(token);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioRespDto> cadastro(@RequestBody @Valid UsuarioReqDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrarAdministrador(dto));
    }
}
