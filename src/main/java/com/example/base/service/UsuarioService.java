package com.example.base.service;


import com.example.base.dto.request.UsuarioReqDto;
import com.example.base.dto.response.UsuarioRespDto;

import java.util.List;

public interface UsuarioService {

    UsuarioRespDto cadastrarAdministrador (UsuarioReqDto usuarioReqDto);
    List<UsuarioRespDto> listarAdministradores ();
}
