package org.example.GlobalSolutionFinal.dto;

import java.util.List;

public class RecomendacaoRequest {
    private List<String> alertasAtivos;
    private List<String> eventosRisco;

    public List<String> getAlertasAtivos() {
        return alertasAtivos;
    }

    public void setAlertasAtivos(List<String> alertasAtivos) {
        this.alertasAtivos = alertasAtivos;
    }

    public List<String> getEventosRisco() {
        return eventosRisco;
    }

    public void setEventosRisco(List<String> eventosRisco) {
        this.eventosRisco = eventosRisco;
    }
}
