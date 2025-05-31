package org.example.GlobalSolutionFinal.controller;

import org.example.GlobalSolutionFinal.model.RotaSegura;
import org.example.GlobalSolutionFinal.service.RotaSeguraService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rotas")
public class RotaSeguraController {

    private final RotaSeguraService service;

    public RotaSeguraController(RotaSeguraService service) {
        this.service = service;
    }

    @GetMapping
    public String listarRotas(Model model) {
        model.addAttribute("rotas", service.findAll());
        return "rotas/listar";
    }

    @GetMapping("/novo")
    public String novaRotaForm(Model model) {
        model.addAttribute("rota", new RotaSegura());
        return "rotas/form";
    }

    @PostMapping("/salvar")
    public String salvarRota(@Valid @ModelAttribute("rota") RotaSegura rota,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "rotas/form";
        }
        service.save(rota);
        return "redirect:/rotas";
    }

    @GetMapping("/editar/{id}")
    public String editarRotaForm(@PathVariable Long id, Model model) {
        var rota = service.findById(id).orElseThrow(() -> new IllegalArgumentException("Rota inválida: " + id));
        model.addAttribute("rota", rota);
        return "rotas/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluirRota(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/rotas";
    }
}
