package org.example.GlobalSolutionFinal.service;

import org.example.GlobalSolutionFinal.model.RotaSegura;
import org.example.GlobalSolutionFinal.repository.RotaSeguraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RotaSeguraService {

    private final RotaSeguraRepository repository;

    public RotaSeguraService(RotaSeguraRepository repository) {
        this.repository = repository;
    }

    public List<RotaSegura> findAll() {
        return repository.findAll();
    }

    public Optional<RotaSegura> findById(Long id) {
        return repository.findById(id);
    }

    public List<RotaSegura> findByStatus(String status) {
        return repository.findByStatus(status);
    }

    public List<RotaSegura> findByNomeContaining(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public RotaSegura save(RotaSegura rota) {
        return repository.save(rota);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
