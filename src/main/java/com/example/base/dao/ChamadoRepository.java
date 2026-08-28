package com.example.base.dao;

import com.example.base.model.Chamado;
import com.example.base.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    List<Chamado> findByUsuario_Id(Long id);
    List<Chamado> findByStatus(Status status);
    List<Chamado> findByUsuario_IdAndStatus(Long id, Status status);
}
