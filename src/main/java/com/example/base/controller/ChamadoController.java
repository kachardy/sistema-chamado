package com.example.base.controller;


import com.example.base.dto.request.ChamadoReqDto;
import com.example.base.dto.response.ChamadoRespDto;
import com.example.base.model.Status;
import com.example.base.service.ChamadoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<List<ChamadoRespDto>> listarChamadoPorUsuarioId(@PathVariable Long usuarioId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarChamadosPorUsuario_IdEStatus(usuarioId, Status.ABERTO));
    }

    @GetMapping("/status")
    public ResponseEntity<List<ChamadoRespDto>> listarChamadoPorStatus(@RequestParam Status status) {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarChamadoPorStatus(status));
    }

}
