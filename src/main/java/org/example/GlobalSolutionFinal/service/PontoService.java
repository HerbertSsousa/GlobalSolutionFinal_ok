package org.example.GlobalSolutionFinal.service;

import org.example.GlobalSolutionFinal.model.Ponto;
import org.example.GlobalSolutionFinal.repository.PontoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PontoService {

    private final PontoRepository repository;

    public PontoService(PontoRepository repository) {
        this.repository = repository;
    }

    public List<Ponto> findAll() {
        return repository.findAll();
    }

    public Optional<Ponto> findById(Long id) {
        return repository.findById(id);
    }

    public Ponto save(Ponto ponto) {
        return repository.save(ponto);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Ponto> findByRotaId(Long rotaSeguraId) {
        return repository.findByRotaSegura_Id(rotaSeguraId);
    }

}
