package com.example.appmed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "diahora_sessao")
public class DiaHoraSessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDiaHoraSessao;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O dia da semana é obrigatório.")
    @Column(name = "dia_semana", nullable = false)
    private DayOfWeek diaSemana;

    @NotNull(message = "O horário é obrigatório.")
    @Column(name = "hora", nullable = false)
    private LocalTime hora;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.ATIVO;

    @ManyToOne
    @JoinColumn(name = "fk_criar_sessao", nullable = false)
    private CriarSessao criarSessao;

    public enum Status {
        ATIVO, INATIVO
    }

    // Getters e Setters
    public Integer getIdDiaHoraSessao() {
        return idDiaHoraSessao;
    }

    public void setIdDiaHoraSessao(Integer idDiaHoraSessao) {
        this.idDiaHoraSessao = idDiaHoraSessao;
    }

    public DayOfWeek getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DayOfWeek diaSemana) {
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

    public CriarSessao getCriarSessao() {
        return criarSessao;
    }

    public void setCriarSessao(CriarSessao criarSessao) {
        this.criarSessao = criarSessao;
    }
}
