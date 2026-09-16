package com.example.base.dao;

import com.example.base.model.Papel;
import com.example.base.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Boolean existsByEmail(String email);
    Page<Usuario> findAllByPapel(Papel papel, Pageable pageable);
    Optional<Usuario> findByEmail(String email);
}
