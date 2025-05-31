package org.example.GlobalSolutionFinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "EVENTOS_DE_RISCO")
public class EventoDeRisco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_evento")
    @SequenceGenerator(name = "seq_evento", sequenceName = "SEQ_EVENTO", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Column(name = "TIPO", nullable = false, length = 50)
    private String tipo; // Ex: "Incêndio", "Enchente"

    @NotBlank
    @Column(name = "DESCRICAO", nullable = false, columnDefinition = "CLOB")
    private String descricao;

    @NotNull
    @Column(name = "DATA_HORA", nullable = false)
    private LocalDateTime dataHora;

    @NotNull
    @Column(name = "LATITUDE", nullable = false, precision = 10, scale = 6)
    private BigDecimal latitude;

    @NotNull
    @Column(name = "LONGITUDE", nullable = false, precision = 10, scale = 6)
    private BigDecimal longitude;

    @Column(name = "STATUS", length = 20)
    private String status;

    public EventoDeRisco() {}

    public EventoDeRisco(Long id, String tipo, String descricao, LocalDateTime dataHora,
                         BigDecimal latitude, BigDecimal longitude, String status) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public BigDecimal getLatitude() { return latitude; }
    public void setLatitude(BigDecimal latitude) { this.latitude = latitude; }

    public BigDecimal getLongitude() { return longitude; }
    public void setLongitude(BigDecimal longitude) { this.longitude = longitude; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "EventoDeRisco{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataHora=" + dataHora +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", status='" + status + '\'' +
                '}';
    }
}
