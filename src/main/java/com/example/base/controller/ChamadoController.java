package com.example.base.controller;


import com.example.base.dto.UsuarioLogadoDto;
import com.example.base.dto.request.ChamadoReqDto;
import com.example.base.dto.response.ChamadoRespDto;
import com.example.base.model.Categoria;
import com.example.base.model.Status;
import com.example.base.service.ChamadoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("v1/chamados")
@AllArgsConstructor
public class ChamadoController {

    private final ChamadoService service;

    @PostMapping
    public ResponseEntity<ChamadoRespDto> cadastrarChamado(@RequestBody @Valid ChamadoReqDto chamadoReqDto, @AuthenticationPrincipal UsuarioLogadoDto usuarioLogadoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarChamado(chamadoReqDto, usuarioLogadoDto.id()));
    }

    @GetMapping("/me")
    public ResponseEntity<Page<ChamadoRespDto>> listarChamadoPorUsuarioLogado(
            @AuthenticationPrincipal UsuarioLogadoDto usuarioLogadoDto,
            @PageableDefault(size = 10, sort = "titulo") Pageable pageable,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) String busca) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(service.listarChamadosPorUsuario_IdEStatus(usuarioLogadoDto.id(), status, busca, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoRespDto> buscarChamadoPorId(
            @PathVariable Long id,
            @AuthenticationPrincipal UsuarioLogadoDto usuarioLogadoDto) {

        return ResponseEntity.status(HttpStatus.OK).body(service.buscarChamadoPorId(id, usuarioLogadoDto.id()));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Page<ChamadoRespDto>> listarChamadosAdministrativo(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Categoria categoria,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {

        Page<ChamadoRespDto> resultado = service.listarChamadosAdmin(status, categoria, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(resultado);
    }

    @GetMapping("/status")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Page<ChamadoRespDto>> listarChamadoPorStatus(@RequestParam Status status, @PageableDefault(size = 10, sort = "titulo") Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarChamadoPorStatus(status, pageable));
    }

    @GetMapping("/{usuarioId}/filtro")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Page<ChamadoRespDto>> listarChamadosComFiltro(
            @PathVariable Long usuarioId,
            @RequestParam(required = false) Status status,
            @PageableDefault(size = 10, sort = "titulo") Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(service.listarChamadosDoUsuarioComFiltro(usuarioId, status, pageable));
    }
}
