package org.example.GlobalSolutionFinal.repository;

import org.example.GlobalSolutionFinal.model.RotaSegura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RotaSeguraRepository extends JpaRepository<RotaSegura, Long> {

    // Buscar rotas por status
    List<RotaSegura> findByStatus(String status);

    // Buscar rotas pelo nome contendo termo ignorando case
    List<RotaSegura> findByNomeContainingIgnoreCase(String nome);
}
