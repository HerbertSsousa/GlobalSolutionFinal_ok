package org.example.GlobalSolutionFinal.repository;

import org.example.GlobalSolutionFinal.model.EventoDeRisco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoDeRiscoRepository extends JpaRepository<EventoDeRisco, Long> {

    // Buscar eventos ativos
    List<EventoDeRisco> findByStatus(String status);

    // Buscar eventos por tipo
    List<EventoDeRisco> findByTipo(String tipo);

    // Buscar eventos ordenados por dataHora descendente (mais recentes)
    List<EventoDeRisco> findAllByOrderByDataHoraDesc();
}
