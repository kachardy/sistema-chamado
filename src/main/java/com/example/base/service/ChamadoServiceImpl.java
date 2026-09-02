package com.example.base.service;

import com.example.base.dao.ChamadoRepository;
import com.example.base.dao.UsuarioRepository;
import com.example.base.dto.request.ChamadoReqDto;
import com.example.base.dto.response.ChamadoRespDto;
import com.example.base.dto.response.UsuarioRespDto;
import com.example.base.exception.RecursoNaoEncontradoException;
import com.example.base.model.Chamado;
import com.example.base.model.Status;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.security.InvalidParameterException;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ChamadoServiceImpl implements ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    @Override
    public ChamadoRespDto cadastrarChamado(ChamadoReqDto chamadoReqDto, Long id) {
        var usuarioBuscado = usuarioRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Usuário com ID " + id + " não encontrado."));


        Chamado chamado = new Chamado();
        chamado.setTitulo(chamadoReqDto.titulo());
        chamado.setDescricao(chamadoReqDto.descricao());
        chamado.setPrioridade(chamadoReqDto.prioridade());
        chamado.setStatus(Status.ABERTO);
        chamado.setUsuario(usuarioBuscado);
        chamado.setCategoria(chamadoReqDto.categoria());

        var chamadoSalvo = chamadoRepository.save(chamado);

        UsuarioRespDto usuarioRespDto = new UsuarioRespDto(usuarioBuscado.getId(), usuarioBuscado.getNome(), usuarioBuscado.getEmail());
        return new ChamadoRespDto(chamadoSalvo.getId(), chamadoSalvo.getTitulo(), chamadoSalvo.getDescricao(), chamadoSalvo.getPrioridade(), chamadoSalvo.getStatus(), usuarioRespDto, chamadoSalvo.getCategoria());
    }

    @Override
    public Page<ChamadoRespDto> listarChamadoPorUsuario_Id(Long id, Pageable pageable) {

        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Usuário com ID " + id + " não encontrado.");
        }

        Page<Chamado> chamados = chamadoRepository.findByUsuario_Id(id, pageable);

        return chamados.map(chamado ->
                new ChamadoRespDto(
                        chamado.getId(),
                        chamado.getTitulo(),
                        chamado.getDescricao(),
                        chamado.getPrioridade(),
                        chamado.getStatus(),
                        new UsuarioRespDto((chamado.getUsuario().getId()), chamado.getUsuario().getNome(), chamado.getUsuario().getEmail()),
                        chamado.getCategoria()
                )
        );

    }

    @Override
    public Page<ChamadoRespDto> listarChamadoPorStatus(Status status, Pageable pageable) {

        Page<Chamado> chamados = chamadoRepository.findByStatus(status, pageable);

        return chamados.map(chamado ->
                new ChamadoRespDto(
                        chamado.getId(),
                        chamado.getTitulo(),
                        chamado.getDescricao(),
                        chamado.getPrioridade(),
                        chamado.getStatus(),
                        new UsuarioRespDto((chamado.getUsuario().getId()), chamado.getUsuario().getNome(), chamado.getUsuario().getEmail()),
                        chamado.getCategoria()
                )
        );

    }

    @Override
    public Page<ChamadoRespDto> listarChamadosPorUsuario_IdEStatus(Long id, Status status, Pageable pageable) {

        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Usuário com ID " + id + " não encontrado.");
        }

        Page<Chamado> chamados = chamadoRepository.findByUsuario_IdAndStatus(id, status, pageable);

        return chamados.map(chamado ->
                new ChamadoRespDto(chamado.getId(),
                        chamado.getTitulo(),
                        chamado.getDescricao(),
                        chamado.getPrioridade(),
                        chamado.getStatus(),
                        new UsuarioRespDto((chamado.getUsuario().getId()), chamado.getUsuario().getNome(), chamado.getUsuario().getEmail()),
                        chamado.getCategoria()
                )
        );
    }

    @Override
    public Page<ChamadoRespDto> listarChamadosDoUsuarioComFiltro(Long id, Status status, Pageable pageable) {

        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Usuário com ID " + id + " não encontrado.");
        }

        Page<Chamado> chamados;

        if (status == null) {
            chamados = chamadoRepository.findByUsuario_Id(id, pageable);
        } else {
            chamados = chamadoRepository.findByUsuario_IdAndStatus(id, status, pageable);
        }

        return chamados.map(chamado -> new ChamadoRespDto(
                chamado.getId(),
                chamado.getTitulo(),
                chamado.getDescricao(),
                chamado.getPrioridade(),
                chamado.getStatus(),
                new UsuarioRespDto(chamado.getUsuario().getId(), chamado.getUsuario().getNome(), chamado.getUsuario().getEmail()),
                chamado.getCategoria()
        ));
    }


}
