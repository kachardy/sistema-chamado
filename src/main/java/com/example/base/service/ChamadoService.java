package com.example.base.service;

import com.example.base.dto.request.ChamadoReqDto;
import com.example.base.dto.response.ChamadoRespDto;
import com.example.base.model.Status;

import java.util.List;

public interface ChamadoService {
    ChamadoRespDto cadastrarChamado(ChamadoReqDto chamadoReqDto, Long id);
    List<ChamadoRespDto> listarChamadoPorUsuario_Id(Long id);
    List<ChamadoRespDto> listarChamadoPorStatus(Status status);
}
