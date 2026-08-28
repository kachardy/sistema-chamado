package com.example.base.service;

import com.example.base.dao.ChamadoRepository;
import com.example.base.dao.UsuarioRepository;
import com.example.base.dto.request.ChamadoReqDto;
import com.example.base.dto.response.ChamadoRespDto;
import com.example.base.dto.response.UsuarioRespDto;
import com.example.base.model.Chamado;
import com.example.base.model.Status;
import lombok.AllArgsConstructor;
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
        var usuarioBuscado = usuarioRepository.findById(id).orElseThrow(() -> new InvalidParameterException());


        Chamado chamado = new Chamado();
        chamado.setTitulo(chamadoReqDto.titulo());
        chamado.setDescricao(chamadoReqDto.descricao());
        chamado.setPrioridade(chamadoReqDto.prioridade());
        chamado.setStatus(Status.ABERTO);
        chamado.setUsuario(usuarioBuscado);

        var chamadoSalvo = chamadoRepository.save(chamado);

        UsuarioRespDto usuarioRespDto = new UsuarioRespDto(usuarioBuscado.getId(), usuarioBuscado.getNome(), usuarioBuscado.getEmail());
        return new ChamadoRespDto(chamadoSalvo.getId(), chamadoSalvo.getTitulo(), chamadoSalvo.getDescricao(), chamadoSalvo.getPrioridade(), chamadoSalvo.getStatus(), usuarioRespDto);
    }

    @Override
    public List<ChamadoRespDto> listarChamadoPorUsuario_Id(Long id) {
        List<Chamado> chamados = chamadoRepository.findByUsuario_Id(id);

        return chamados.stream()
                .map(chamado -> new ChamadoRespDto(chamado.getId(), chamado.getTitulo(), chamado.getDescricao(), chamado.getPrioridade(), chamado.getStatus(), new UsuarioRespDto((chamado.getUsuario().getId()), chamado.getUsuario().getNome(), chamado.getUsuario().getEmail())))
                .toList();
    }

    @Override
    public List<ChamadoRespDto> listarChamadoPorStatus(Status status) {
        List<Chamado> chamados = chamadoRepository.findByStatus(status);

        return chamados.stream()
                .map(chamado -> new ChamadoRespDto(chamado.getId(), chamado.getTitulo(), chamado.getDescricao(), chamado.getPrioridade(), chamado.getStatus(), new UsuarioRespDto((chamado.getUsuario().getId()), chamado.getUsuario().getNome(), chamado.getUsuario().getEmail())))
                .toList();
    }

    @Override
    public List<ChamadoRespDto> listarChamadosPorUsuario_IdEStatus(Long id, Status status) {
        List<Chamado> chamados = chamadoRepository.findByUsuario_IdAndStatus(id, status);

        return chamados.stream()
                .map(chamado -> new ChamadoRespDto(chamado.getId(), chamado.getTitulo(), chamado.getDescricao(), chamado.getPrioridade(), chamado.getStatus(), new UsuarioRespDto((chamado.getUsuario().getId()), chamado.getUsuario().getNome(), chamado.getUsuario().getEmail())))
                .toList();
    }


}
