package com.example.appmed.dto;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MonitoramentoResponseDTO {

    private Integer idMonitoramento;
    private Long usuarioId;
    private Long sessaoId;
    private LocalDateTime dataHora;
    private LocalDate dataMonitoramento;
    private LocalTime horaMonitoramento;
    private BigDecimal valor;
    private DayOfWeek diaSemana;
    private LocalTime horarioConfigurado;

    public Integer getIdMonitoramento() {
        return idMonitoramento;
    }

    public void setIdMonitoramento(Integer idMonitoramento) {
        this.idMonitoramento = idMonitoramento;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getSessaoId() {
        return sessaoId;
    }

    public void setSessaoId(Long sessaoId) {
        this.sessaoId = sessaoId;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public DayOfWeek getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DayOfWeek diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHorarioConfigurado() {
        return horarioConfigurado;
    }

    public void setHorarioConfigurado(LocalTime horarioConfigurado) {
        this.horarioConfigurado = horarioConfigurado;
    }

    public MonitoramentoResponseDTO(Integer idMonitoramento, Long usuarioId, Long sessaoId,
                                    LocalDateTime dataHora, LocalDate dataMonitoramento,
                                    LocalTime horaMonitoramento, BigDecimal valor,
                                    DayOfWeek diaSemana, LocalTime horarioConfigurado) {
        this.idMonitoramento = idMonitoramento;
        this.usuarioId = usuarioId;
        this.sessaoId = sessaoId;
        this.dataHora = dataHora;
        this.dataMonitoramento = dataMonitoramento;
        this.horaMonitoramento = horaMonitoramento;
        this.valor = valor;
        this.diaSemana = diaSemana;
        this.horarioConfigurado = horarioConfigurado;
    }

    
}
