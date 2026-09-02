package com.example.base.service;

import com.example.base.dto.request.ChamadoReqDto;
import com.example.base.dto.response.ChamadoRespDto;
import com.example.base.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChamadoService {
    ChamadoRespDto cadastrarChamado(ChamadoReqDto chamadoReqDto, Long id);
    Page<ChamadoRespDto> listarChamadoPorUsuario_Id(Long id, Pageable pageable);
    Page<ChamadoRespDto> listarChamadoPorStatus(Status status, Pageable pageable);
    Page<ChamadoRespDto> listarChamadosPorUsuario_IdEStatus(Long id, Status status, Pageable pageable);
    Page<ChamadoRespDto> listarChamadosDoUsuarioComFiltro(Long id, Status status, Pageable pageable);
}
