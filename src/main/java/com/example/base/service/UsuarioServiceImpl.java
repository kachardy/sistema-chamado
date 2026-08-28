package com.example.base.service;

import com.example.base.dao.UsuarioRepository;
import com.example.base.dto.request.UsuarioReqDto;
import com.example.base.dto.response.UsuarioRespDto;
import com.example.base.model.Papel;
import com.example.base.model.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    @Override
    public UsuarioRespDto cadastrarAdministrador(UsuarioReqDto usuarioReqDto) {

        validarDadosCadastro(usuarioReqDto);

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioReqDto.nome());
        usuario.setEmail(usuarioReqDto.email());
        usuario.setSenha(usuarioReqDto.senha());
        usuario.setPapel(Papel.ADMIN);

        var usuarioSalvo = repository.save(usuario);

        return new UsuarioRespDto(usuarioSalvo.getId(), usuarioSalvo.getNome(), usuarioSalvo.getEmail());
    }

    @Override
    public List<UsuarioRespDto> listarAdministradores() {
        List<Usuario> usuarios = repository.findAllByPapel(Papel.ADMIN);

        return usuarios.stream()
                .map(usuario -> new UsuarioRespDto(usuario.getId(), usuario.getNome(), usuario.getEmail()))
                .toList();
    }

    private void validarDadosCadastro(UsuarioReqDto dto) {
        if (!dto.senha().equals(dto.confirmacaoSenha())) {
            throw new IllegalArgumentException("As senhas não conferem!");
        }
        if (repository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Este e-mail já está cadastrado no sistema.");
        }
    }
}
