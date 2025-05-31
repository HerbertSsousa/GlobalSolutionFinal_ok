package org.example.GlobalSolutionFinal.controller;

import org.example.GlobalSolutionFinal.model.Ponto;
import org.example.GlobalSolutionFinal.service.PontoService;
import org.example.GlobalSolutionFinal.service.RotaSeguraService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pontos")
public class PontoController {

    private final PontoService service;
    private final RotaSeguraService rotaService;

    public PontoController(PontoService service, RotaSeguraService rotaService) {
        this.service = service;
        this.rotaService = rotaService;
    }

    @GetMapping
    public String listarPontos(Model model) {
        model.addAttribute("pontos", service.findAll());
        return "pontos/listar";
    }

    @GetMapping("/novo")
    public String novoPontoForm(Model model) {
        model.addAttribute("ponto", new Ponto());
        model.addAttribute("rotas", rotaService.findAll()); // para popular dropdown no form, se usar
        return "pontos/form";
    }

    @PostMapping("/salvar")
    public String salvarPonto(@Valid @ModelAttribute("ponto") Ponto ponto,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("rotas", rotaService.findAll());
            return "pontos/form";
        }
        service.save(ponto);
        return "redirect:/pontos";
    }

    @GetMapping("/editar/{id}")
    public String editarPontoForm(@PathVariable Long id, Model model) {
        var ponto = service.findById(id).orElseThrow(() -> new IllegalArgumentException("Ponto inválido: " + id));
        model.addAttribute("ponto", ponto);
        model.addAttribute("rotas", rotaService.findAll());
        return "pontos/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluirPonto(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/pontos";
    }

    // Caso queira listar pontos por rota (opcional)
    @GetMapping("/rota/{rotaId}")
    public String listarPontosPorRota(@PathVariable Long rotaId, Model model) {
        List<Ponto> pontos = service.findByRotaId(rotaId);
        model.addAttribute("pontos", pontos);
        model.addAttribute("rotaId", rotaId);
        return "pontos/listar";
    }
}
