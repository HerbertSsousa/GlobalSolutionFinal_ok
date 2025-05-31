package org.example.GlobalSolutionFinal.service;

import org.example.GlobalSolutionFinal.model.EventoDeRisco;
import org.example.GlobalSolutionFinal.repository.EventoDeRiscoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoDeRiscoService {

    private final EventoDeRiscoRepository repository;

    public EventoDeRiscoService(EventoDeRiscoRepository repository) {
        this.repository = repository;
    }

    public List<EventoDeRisco> findAll() {
        return repository.findAll();
    }

    public Optional<EventoDeRisco> findById(Long id) {
        return repository.findById(id);
    }

    public List<EventoDeRisco> findByStatus(String status) {
        return repository.findByStatus(status);
    }

    public List<EventoDeRisco> findByTipo(String tipo) {
        return repository.findByTipo(tipo);
    }

    public List<EventoDeRisco> findRecentes() {
        return repository.findAllByOrderByDataHoraDesc();
    }

    public EventoDeRisco save(EventoDeRisco evento) {
        return repository.save(evento);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
