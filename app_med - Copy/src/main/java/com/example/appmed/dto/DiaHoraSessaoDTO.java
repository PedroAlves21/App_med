package com.example.appmed.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DiaHoraSessaoDTO {
    private Long idDiaHoraSessao;
    private DayOfWeek diaSemana;
    private LocalTime hora;
    private Long inputId; // ID do input

    // Construtor
    public DiaHoraSessaoDTO(Long idDiaHoraSessao, DayOfWeek diaSemana, LocalTime hora, Long inputId) {
        this.idDiaHoraSessao = idDiaHoraSessao;
        this.diaSemana = diaSemana;
        this.hora = hora;
        this.inputId = inputId;
    }

    // Getters e Setters
    public Long getIdDiaHoraSessao() {
        return idDiaHoraSessao;
    }

    public void setIdDiaHoraSessao(Long idDiaHoraSessao) {
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

    public Long getInputId() {
        return inputId;
    }

    public void setInputId(Long inputId) {
        this.inputId = inputId;
    }
}
