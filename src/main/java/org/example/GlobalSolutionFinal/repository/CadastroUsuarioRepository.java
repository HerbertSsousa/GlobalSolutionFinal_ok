package org.example.GlobalSolutionFinal.repository;

import org.example.GlobalSolutionFinal.model.CadastroUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CadastroUsuarioRepository extends JpaRepository<CadastroUsuario, Long> {

    // Busca por username (útil para login, validações)
    Optional<CadastroUsuario> findByUsername(String username);

    // Busca por email
    Optional<CadastroUsuario> findByEmail(String email);

    // Verifica existência por username
    boolean existsByUsername(String username);

    // Verifica existência por email
    boolean existsByEmail(String email);
}
