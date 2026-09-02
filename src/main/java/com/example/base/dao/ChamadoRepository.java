package com.example.base.dao;

import com.example.base.model.Chamado;
import com.example.base.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    Page<Chamado> findByUsuario_Id(Long id, Pageable pageable);
    Page<Chamado> findByStatus(Status status, Pageable pageable);
    Page<Chamado> findByUsuario_IdAndStatus(Long id, Status status, Pageable pageable);
}
