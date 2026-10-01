package com.example.base.controller;

import com.example.base.dto.request.LoginReqDto;
import com.example.base.dto.request.UsuarioReqDto;
import com.example.base.dto.response.UsuarioRespDto;
import com.example.base.service.AuthService;
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

    private final AuthService authService;
    private final UsuarioService usuarioService;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginReqDto dto) {
        String token = authService.login(dto);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioRespDto> cadastro(@RequestBody @Valid UsuarioReqDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrarUsuario(dto));
    }
}
