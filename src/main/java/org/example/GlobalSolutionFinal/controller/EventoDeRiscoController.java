package org.example.GlobalSolutionFinal.controller;

import org.example.GlobalSolutionFinal.model.EventoDeRisco;
import org.example.GlobalSolutionFinal.service.EventoDeRiscoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/eventos")
public class EventoDeRiscoController {

    private final EventoDeRiscoService service;

    public EventoDeRiscoController(EventoDeRiscoService service) {
        this.service = service;
    }

    @GetMapping
    public String listarEventos(Model model) {
        model.addAttribute("eventos", service.findAll());
        return "eventos/listar";
    }

    @GetMapping("/novo")
    public String novoEventoForm(Model model) {
        model.addAttribute("evento", new EventoDeRisco());
        return "eventos/form";
    }

    @PostMapping("/salvar")
    public String salvarEvento(@Valid @ModelAttribute("evento") EventoDeRisco evento,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "eventos/form";
        }
        service.save(evento);
        return "redirect:/eventos";
    }

    @GetMapping("/editar/{id}")
    public String editarEventoForm(@PathVariable Long id, Model model) {
        var evento = service.findById(id).orElseThrow(() -> new IllegalArgumentException("Evento inválido: " + id));
        model.addAttribute("evento", evento);
        return "eventos/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluirEvento(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/eventos";
    }
}
