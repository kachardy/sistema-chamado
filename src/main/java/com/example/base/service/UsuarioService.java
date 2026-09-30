package com.example.base.service;


import com.example.base.dto.request.UsuarioReqDto;
import com.example.base.dto.response.UsuarioRespDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UsuarioService {

    UsuarioRespDto cadastrarAdministrador (UsuarioReqDto usuarioReqDto);
    UsuarioRespDto cadastrarUsuario (UsuarioReqDto usuarioReqDto);
    Page<UsuarioRespDto> listarAdministradores (Pageable pageable);
}
