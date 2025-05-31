package org.example.GlobalSolutionFinal.controller;

import org.example.GlobalSolutionFinal.model.Alerta;
import org.example.GlobalSolutionFinal.model.EventoDeRisco;
import org.example.GlobalSolutionFinal.service.AlertaService;
import org.example.GlobalSolutionFinal.service.EventoDeRiscoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DashboardController {

    private final AlertaService alertaService;
    private final EventoDeRiscoService eventoService;

    public DashboardController(AlertaService alertaService, EventoDeRiscoService eventoService) {
        this.alertaService = alertaService;
        this.eventoService = eventoService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Exibir dashboard tradicional (HTML)
        return "dashboard";
    }

    // Retorna alertas ativos (exemplo filtro pode ser ajustado)
    @GetMapping("/api/alertas/ativos")
    @ResponseBody
    public List<Alerta> listarAlertasAtivos() {
        // Poderia ser um filtro real, aqui só um exemplo simples
        return alertaService.findAll();
    }

    // Retorna eventos ativos
    @GetMapping("/api/eventos/ativos")
    @ResponseBody
    public List<EventoDeRisco> listarEventosAtivos() {
        return eventoService.findAll();
    }
}
