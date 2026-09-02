package com.example.base.controller;


import com.example.base.dto.request.ChamadoReqDto;
import com.example.base.dto.response.ChamadoRespDto;
import com.example.base.model.Status;
import com.example.base.service.ChamadoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chamado")
@AllArgsConstructor
public class ChamadoController {

    private final ChamadoService service;

    @PostMapping("/usuarios/{usuarioId}/chamados")
    public ResponseEntity<ChamadoRespDto> cadastrarChamado(@RequestBody @Valid ChamadoReqDto chamadoReqDto, @PathVariable Long usuarioId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarChamado(chamadoReqDto, usuarioId));
    }

    @GetMapping("/usuarios/{usuarioId}/chamados/abertos")
    public ResponseEntity<Page<ChamadoRespDto>> listarChamadoPorUsuarioId(@PathVariable Long usuarioId, @PageableDefault(size = 10, sort = "titulo") Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarChamadosPorUsuario_IdEStatus(usuarioId, Status.ABERTO, pageable));
    }

    @GetMapping("/status")
    public ResponseEntity<Page<ChamadoRespDto>> listarChamadoPorStatus(@RequestParam Status status, @PageableDefault(size = 10, sort = "titulo") Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarChamadoPorStatus(status, pageable));
    }

    @GetMapping("/usuarios/{usuarioId}/chamados")
    public ResponseEntity<Page<ChamadoRespDto>> listarChamadosComFiltro(
            @PathVariable Long usuarioId,
            @RequestParam(required = false) Status status,
            @PageableDefault(size = 10, sort = "titulo") Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(service.listarChamadosDoUsuarioComFiltro(usuarioId, status, pageable));
    }
}
