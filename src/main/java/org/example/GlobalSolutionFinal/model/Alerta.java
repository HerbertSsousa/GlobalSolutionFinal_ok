package org.example.GlobalSolutionFinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ALERTAS")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_alerta")
    @SequenceGenerator(name = "seq_alerta", sequenceName = "SEQ_ALERTA", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private CadastroUsuario usuario;

    @ManyToOne
    @JoinColumn(name = "EVENTO_ID", nullable = false)
    private EventoDeRisco evento;

    @NotBlank
    @Column(name = "MENSAGEM", nullable = false, columnDefinition = "CLOB")
    private String mensagem;

    @NotNull
    @Column(name = "DATA_HORA_ENVIO", nullable = false)
    private LocalDateTime dataHoraEnvio;

    @Column(name = "LIDO")
    private Boolean lido = false;

    // Construtores

    public Alerta() {}

    public Alerta(Long id, CadastroUsuario usuario, EventoDeRisco evento, String mensagem, LocalDateTime dataHoraEnvio, Boolean lido) {
        this.id = id;
        this.usuario = usuario;
        this.evento = evento;
        this.mensagem = mensagem;
        this.dataHoraEnvio = dataHoraEnvio;
        this.lido = lido;
    }

    // Getters e Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public CadastroUsuario getUsuario() { return usuario; }
    public void setUsuario(CadastroUsuario usuario) { this.usuario = usuario; }

    public EventoDeRisco getEvento() { return evento; }
    public void setEvento(EventoDeRisco evento) { this.evento = evento; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public LocalDateTime getDataHoraEnvio() { return dataHoraEnvio; }
    public void setDataHoraEnvio(LocalDateTime dataHoraEnvio) { this.dataHoraEnvio = dataHoraEnvio; }

    public Boolean getLido() { return lido; }
    public void setLido(Boolean lido) { this.lido = lido; }

    @Override
    public String toString() {
        return "Alerta{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", evento=" + evento +
                ", mensagem='" + mensagem + '\'' +
                ", dataHoraEnvio=" + dataHoraEnvio +
                ", lido=" + lido +
                '}';
    }
}