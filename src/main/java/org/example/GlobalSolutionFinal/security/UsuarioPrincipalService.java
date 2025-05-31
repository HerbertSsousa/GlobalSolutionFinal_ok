package org.example.GlobalSolutionFinal.security;

import org.example.GlobalSolutionFinal.model.CadastroUsuario;
import org.example.GlobalSolutionFinal.service.CadastroUsuarioService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioPrincipalService implements UserDetailsService {

    private final CadastroUsuarioService usuarioService;

    public UsuarioPrincipalService(CadastroUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CadastroUsuario usuario = usuarioService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return new UsuarioPrincipal(usuario);
    }
}
