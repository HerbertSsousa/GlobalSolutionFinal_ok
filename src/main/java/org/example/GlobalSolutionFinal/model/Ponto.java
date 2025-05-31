package org.example.GlobalSolutionFinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PONTOS")
public class Ponto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ponto")
    @SequenceGenerator(name = "seq_ponto", sequenceName = "SEQ_PONTO", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "LATITUDE", nullable = false, precision = 10, scale = 6)
    private BigDecimal latitude;

    @NotNull
    @Column(name = "LONGITUDE", nullable = false, precision = 10, scale = 6)
    private BigDecimal longitude;

    @Column(name = "DESCRICAO", length = 255)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROTA_ID")
    private RotaSegura rotaSegura;

    public Ponto() {}

    public Ponto(Long id, BigDecimal latitude, BigDecimal longitude, String descricao, RotaSegura rotaSegura) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.descricao = descricao;
        this.rotaSegura = rotaSegura;
    }

    // getters e setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public BigDecimal getLatitude() { return latitude; }
    public void setLatitude(BigDecimal latitude) { this.latitude = latitude; }

    public BigDecimal getLongitude() { return longitude; }
    public void setLongitude(BigDecimal longitude) { this.longitude = longitude; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public RotaSegura getRotaSegura() { return rotaSegura; }
    public void setRotaSegura(RotaSegura rotaSegura) { this.rotaSegura = rotaSegura; }

    @Override
    public String toString() {
        return "Ponto{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", descricao='" + descricao + '\'' +
                ", rotaSegura=" + (rotaSegura != null ? rotaSegura.getId() : "null") +
                '}';
    }
}
