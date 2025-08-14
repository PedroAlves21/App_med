package com.example.appmed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tipo_sessao")
public class TipoSessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_sessao") // Nome da coluna atualizado conforme prática comum
    private Long idTipoSessao;

    @NotBlank(message = "Nome é obrigatório.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    @Column(name = "nome", nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.ATIVO;

    public enum Status {
        ATIVO, INATIVO
    }

    // Getters e Setters
    public Long getIdTipoSessao() {
        return idTipoSessao;
    }

    public void setIdTipoSessao(Long idTipoSessao) {
        this.idTipoSessao = idTipoSessao;
    }

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
