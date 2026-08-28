package com.example.base.dao;

import com.example.base.model.Papel;
import com.example.base.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Boolean existsByEmail(String email);
    List<Usuario> findAllByPapel(Papel papel);
}
