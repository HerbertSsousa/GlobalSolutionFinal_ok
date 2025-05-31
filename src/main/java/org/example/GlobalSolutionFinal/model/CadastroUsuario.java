package org.example.GlobalSolutionFinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "CADASTRO_USUARIO")
public class CadastroUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    @SequenceGenerator(name = "seq_usuario", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Column(name = "USERNAME", nullable = false, unique = true, length = 50)
    private String username;

    @NotBlank
    @Column(name = "NOME_COMPLETO", nullable = false, length = 100)
    private String nomeCompleto;

    @Email
    @NotBlank
    @Column(name = "EMAIL", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "TELEFONE", length = 20)
    private String telefone;

    @NotBlank
    @Column(name = "PERFIL", nullable = false, length = 20)
    private String perfil; // Ex: "ADMIN", "USUARIO"

    @NotBlank
    @Column(name = "SENHA", nullable = false, length = 255)
    private String senha; // Senha criptografada

    // Construtores

    public CadastroUsuario() {}

    public CadastroUsuario(Long id, String username, String nomeCompleto, String email, String telefone, String perfil, String senha) {
        this.id = id;
        this.username = username;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.telefone = telefone;
        this.perfil = perfil;
        this.senha = senha;
    }

    // Getters e Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getPerfil() { return perfil; }
    public void setPerfil(String perfil) { this.perfil = perfil; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    @Override
    public String toString() {
        return "CadastroUsuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", perfil='" + perfil + '\'' +
                ", senha='[PROTECTED]'" +
                '}';
    }
}
