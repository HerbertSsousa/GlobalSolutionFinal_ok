package org.example.GlobalSolutionFinal.controller;

import org.example.GlobalSolutionFinal.model.CadastroUsuario;
import org.example.GlobalSolutionFinal.service.CadastroUsuarioService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class CadastroUsuarioController {

    private final CadastroUsuarioService service;
    private final PasswordEncoder passwordEncoder;

    public CadastroUsuarioController(CadastroUsuarioService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    // Listagem de usuários e formulário inicial (vazio)
    @GetMapping
    public String listarUsuarios(Model model, @ModelAttribute("successMessage") String successMessage) {
        model.addAttribute("usuarios", service.findAll());
        model.addAttribute("usuario", new CadastroUsuario());  // formulário vazio
        model.addAttribute("successMessage", successMessage);
        return "usuarios/usuariocadastro";
    }

    // Formulário para novo usuário (pode ser mantido ou removido, pois lista já inicializa formulário)
    @GetMapping("/novo")
    public String novoUsuarioForm(Model model) {
        model.addAttribute("usuario", new CadastroUsuario());
        model.addAttribute("usuarios", service.findAll());
        return "usuarios/usuariocadastro";
    }

    // Formulário para editar usuário existente
    @GetMapping("/editar/{id}")
    public String editarUsuarioForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        var optionalUsuario = service.findById(id);
        if (optionalUsuario.isEmpty()) {
            redirectAttributes.addFlashAttribute("successMessage", "Usuário não encontrado para edição.");
            return "redirect:/usuarios";
        }
        model.addAttribute("usuario", optionalUsuario.get());
        model.addAttribute("usuarios", service.findAll());
        return "usuarios/usuariocadastro";
    }

    // Salvar novo ou editar usuário - **sem redirect**, retorna a mesma página
    @PostMapping("/salvar")
    public String salvarUsuario(
            @Valid @ModelAttribute("usuario") CadastroUsuario usuario,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("usuarios", service.findAll());
            return "usuarios/usuariocadastro";
        }

        // Se estiver editando
        if (usuario.getId() != null) {
            var usuarioExistenteOpt = service.findById(usuario.getId());
            if (usuarioExistenteOpt.isEmpty()) {
                model.addAttribute("successMessage", "Usuário não encontrado para edição.");
                model.addAttribute("usuarios", service.findAll());
                return "usuarios/usuariocadastro";
            }
            var usuarioExistente = usuarioExistenteOpt.get();

            if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
                usuario.setSenha(usuarioExistente.getSenha());
            } else {
                usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            }
        } else {
            // Novo usuário: codifica senha
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        service.save(usuario);

        model.addAttribute("successMessage", "Usuário cadastrado com sucesso!");
        model.addAttribute("usuario", new CadastroUsuario());  // limpa formulário
        model.addAttribute("usuarios", service.findAll());

        return "usuarios/usuariocadastro";
    }

    // Exclusão de usuário (POST) - mantém redirect para evitar repost
    @PostMapping("/excluir")
    public String excluirUsuario(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        service.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Usuário excluído com sucesso!");
        return "redirect:/usuarios";
    }
}
