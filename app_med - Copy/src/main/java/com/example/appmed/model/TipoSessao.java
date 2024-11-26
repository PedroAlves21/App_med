package com.example.appmed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tipo_sessao")
public class TipoSessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_sessao")
    private Long idTipoSessao;

    @NotBlank(message = "O nome do tipo de sessão é obrigatório")
    @Column(nullable = false, unique = true)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ATIVO;

    public enum Status {
        ATIVO, INATIVO
    }

    // Construtor padrão
    public TipoSessao() {}

    // Construtor com nome
    public TipoSessao(String nome) {
        this.nome = nome;
        this.status = Status.ATIVO;
    }

    // Método para acessar o ID diretamente
    public Long getIdTipoSessao() {
        return idTipoSessao;
    }

    // Método para definir o ID
    public void setIdTipoSessao(Long idTipoSessao) {
        this.idTipoSessao = idTipoSessao;
    }

    // Getters e Setters para os outros campos
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
