package org.example.GlobalSolutionFinal.service;

import org.example.GlobalSolutionFinal.model.CadastroUsuario;
import org.example.GlobalSolutionFinal.repository.CadastroUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroUsuarioService {

    private final CadastroUsuarioRepository repository;

    public CadastroUsuarioService(CadastroUsuarioRepository repository) {
        this.repository = repository;
    }

    public List<CadastroUsuario> findAll() {
        return repository.findAll();
    }

    public Optional<CadastroUsuario> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<CadastroUsuario> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public CadastroUsuario save(CadastroUsuario usuario) {
        // Aqui poderia ter regras de negócio, validações extras etc.
        return repository.save(usuario);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
