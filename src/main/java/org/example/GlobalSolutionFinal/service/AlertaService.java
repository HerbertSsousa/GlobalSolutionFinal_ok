package org.example.GlobalSolutionFinal.service;

import org.example.GlobalSolutionFinal.model.Alerta;
import org.example.GlobalSolutionFinal.repository.AlertaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertaService {

    private final AlertaRepository repository;

    public AlertaService(AlertaRepository repository) {
        this.repository = repository;
    }

    public List<Alerta> findAll() {
        return repository.findAll();
    }

    public Optional<Alerta> findById(Long id) {
        return repository.findById(id);
    }

    public List<Alerta> findByUsuarioId(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public List<Alerta> findByEventoId(Long eventoId) {
        return repository.findByEventoId(eventoId);
    }

    public List<Alerta> findNaoLidosByUsuarioId(Long usuarioId) {
        return repository.findByUsuarioIdAndLidoFalse(usuarioId);
    }

    public List<Alerta> findByUsuarioIdOrderByDataHoraEnvioDesc(Long usuarioId) {
        return repository.findByUsuarioIdOrderByDataHoraEnvioDesc(usuarioId);
    }

    public Alerta save(Alerta alerta) {
        return repository.save(alerta);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
