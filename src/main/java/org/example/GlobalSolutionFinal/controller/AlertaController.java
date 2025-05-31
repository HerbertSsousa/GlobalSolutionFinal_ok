package org.example.GlobalSolutionFinal.controller;

import org.example.GlobalSolutionFinal.model.Alerta;
import org.example.GlobalSolutionFinal.service.AlertaService;
import org.example.GlobalSolutionFinal.service.CadastroUsuarioService;
import org.example.GlobalSolutionFinal.service.EventoDeRiscoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alertas")
public class AlertaController {

    private final AlertaService service;
    private final CadastroUsuarioService usuarioService;
    private final EventoDeRiscoService eventoService;

    public AlertaController(AlertaService service, CadastroUsuarioService usuarioService, EventoDeRiscoService eventoService) {
        this.service = service;
        this.usuarioService = usuarioService;
        this.eventoService = eventoService;
    }

    @GetMapping
    public String listarAlertas(Model model) {
        model.addAttribute("alertas", service.findAll());
        return "alertas/listar";
    }

    @GetMapping("/novo")
    public String novoAlertaForm(Model model) {
        model.addAttribute("alerta", new Alerta());
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("eventos", eventoService.findAll());
        return "alertas/form";
    }

    @PostMapping("/salvar")
    public String salvarAlerta(@Valid @ModelAttribute("alerta") Alerta alerta,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("usuarios", usuarioService.findAll());
            model.addAttribute("eventos", eventoService.findAll());
            return "alertas/form";
        }
        service.save(alerta);
        return "redirect:/alertas";
    }

    @GetMapping("/editar/{id}")
    public String editarAlertaForm(@PathVariable Long id, Model model) {
        var alerta = service.findById(id).orElseThrow(() -> new IllegalArgumentException("Alerta inválido: " + id));
        model.addAttribute("alerta", alerta);
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("eventos", eventoService.findAll());
        return "alertas/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluirAlerta(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/alertas";
    }
}
