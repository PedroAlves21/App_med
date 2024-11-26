package com.example.appmed.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class MonitoramentoRequestDTO {
    private Long diaHoraSessaoId;
    private double valor;
    private LocalDate dataMonitoramento; // Data do input
    private LocalTime horaMonitoramento; // Hora do input

    // Getters e Setters
    public Long getDiaHoraSessaoId() {
        return diaHoraSessaoId;
    }

    public void setDiaHoraSessaoId(Long diaHoraSessaoId) {
        this.diaHoraSessaoId = diaHoraSessaoId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataMonitoramento() {
        return dataMonitoramento;
    }

    public void setDataMonitoramento(LocalDate dataMonitoramento) {
        this.dataMonitoramento = dataMonitoramento;
    }

    public LocalTime getHoraMonitoramento() {
        return horaMonitoramento;
    }

    public void setHoraMonitoramento(LocalTime horaMonitoramento) {
        this.horaMonitoramento = horaMonitoramento;
    }
}
