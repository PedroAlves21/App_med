package com.example.appmed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Table(name = "diaHora_sessao")
public class DiaHoraSessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDiaHoraSessao")
    private Long idDiaHoraSessao;

    @ManyToOne
    @JoinColumn(name = "frkCriarSessao", nullable = false)
    private CriarSessao criarSessao;

    @Enumerated(EnumType.STRING)
    @Column(name = "diaSemana", nullable = false)
    private DiaSemana diaSemana;

    @NotNull
    @Column(name = "hora", nullable = false)
    private LocalTime hora;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.pendente;

    public enum DiaSemana {
        Segunda, Terça, Quarta, Quinta, Sexta, Sábado, Domingo, Todos
    }

    public enum Status {
        ativo, inativo, pendente
    }

    // Getters e Setters
    public Long getIdDiaHoraSessao() {
        return idDiaHoraSessao;
    }

    public void setIdDiaHoraSessao(Long idDiaHoraSessao) {
        this.idDiaHoraSessao = idDiaHoraSessao;
    }

    public CriarSessao getCriarSessao() {
        return criarSessao;
    }

    public void setCriarSessao(CriarSessao criarSessao) {
        this.criarSessao = criarSessao;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
