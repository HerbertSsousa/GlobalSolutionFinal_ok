package org.example.GlobalSolutionFinal.repository;

import org.example.GlobalSolutionFinal.model.Ponto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PontoRepository extends JpaRepository<Ponto, Long> {

    List<Ponto> findByRotaSegura_Id(Long rotaSeguraId);

}
