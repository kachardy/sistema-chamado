package com.example.base.service;

import com.example.base.dao.UsuarioRepository;
import com.example.base.dto.request.UsuarioReqDto;
import com.example.base.dto.response.UsuarioRespDto;
import com.example.base.model.Papel;
import com.example.base.model.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UsuarioRespDto cadastrarAdministrador(UsuarioReqDto usuarioReqDto) {

        validarDadosCadastro(usuarioReqDto);

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioReqDto.nome());
        usuario.setEmail(usuarioReqDto.email());
        usuario.setSenha(passwordEncoder.encode(usuarioReqDto.senha()));
        usuario.setPapel(Papel.ADMIN);

        var usuarioSalvo = repository.save(usuario);

        return new UsuarioRespDto(usuarioSalvo.getId(), usuarioSalvo.getNome(), usuarioSalvo.getEmail());
    }

    @Override
    public Page<UsuarioRespDto> listarAdministradores(Pageable pageable) {
        Page<Usuario> usuarios = repository.findAllByPapel(Papel.ADMIN, pageable);

        return usuarios.map(usuario ->
                new UsuarioRespDto(usuario.getId(), usuario.getNome(), usuario.getEmail())
        );
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
