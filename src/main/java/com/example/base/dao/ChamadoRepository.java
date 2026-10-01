package com.example.base.dao;

import com.example.base.model.Categoria;
import com.example.base.model.Chamado;
import com.example.base.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    Page<Chamado> findByUsuario_Id(Long id, Pageable pageable);
    Page<Chamado> findByStatus(Status status, Pageable pageable);
    Page<Chamado> findByUsuario_IdAndStatus(Long id, Status status, Pageable pageable);

    @Query("SELECT c FROM Chamado c WHERE " +
            "(:status IS NULL OR c.status = :status) AND " +
            "(:categoria IS NULL OR c.categoria = :categoria)")
    Page<Chamado> buscarTodosComFiltros(
            @Param("status") Status status,
            @Param("categoria") Categoria categoria,
            Pageable pageable);

    @Query("SELECT c FROM Chamado c WHERE c.usuario.id = :usuarioId " +
            "AND (:status IS NULL OR c.status = :status) " +
            "AND (:busca IS NULL OR LOWER(c.titulo) LIKE :busca OR LOWER(c.descricao) LIKE :busca)")
    Page<Chamado> buscarMeusChamadosComFiltros(
            @Param("usuarioId") Long usuarioId,
            @Param("status") Status status,
            @Param("busca") String busca,
            Pageable pageable);
}
