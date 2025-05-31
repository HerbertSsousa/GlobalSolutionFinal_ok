package org.example.GlobalSolutionFinal.repository;

import org.example.GlobalSolutionFinal.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {

    // Buscar alertas por usuário
    List<Alerta> findByUsuarioId(Long usuarioId);

    // Buscar alertas por evento
    List<Alerta> findByEventoId(Long eventoId);

    // Buscar alertas por usuário e não lidos
    List<Alerta> findByUsuarioIdAndLidoFalse(Long usuarioId);

    // Buscar alertas ordenados por dataHoraEnvio decrescente
    List<Alerta> findByUsuarioIdOrderByDataHoraEnvioDesc(Long usuarioId);
}
