package org.example.GlobalSolutionFinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "ROTAS_SEGURAS")
public class RotaSegura {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_rota")
    @SequenceGenerator(name = "seq_rota", sequenceName = "SEQ_ROTA", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ROTA_ID")
    private List<Ponto> pontos;

    @Column(name = "DESCRICAO", columnDefinition = "CLOB")
    private String descricao;

    @Column(name = "STATUS", length = 20)
    private String status;

    // Construtores

    public RotaSegura() {}

    public RotaSegura(Long id, String nome, List<Ponto> pontos, String descricao, String status) {
        this.id = id;
        this.nome = nome;
        this.pontos = pontos;
        this.descricao = descricao;
        this.status = status;
    }

    // Getters e Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public List<Ponto> getPontos() { return pontos; }
    public void setPontos(List<Ponto> pontos) { this.pontos = pontos; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "RotaSegura{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", pontos=" + pontos +
                ", descricao='" + descricao + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}