package com.example.appmed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(
    name = "monitoramento",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"fkDiaHoraSessao", "data_monitoramento", "hora_monitoramento"}
    )
)
public class Monitoramento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMonitoramento;

    @ManyToOne
    @JoinColumn(name = "fkUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fkDiaHoraSessao", nullable = false)
    @NotNull(message = "A configuração de dia e hora da sessão não pode estar vazia.")
    private DiaHoraSessao diaHoraSessao;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @DecimalMin(value = "0.0", inclusive = false, message = "O valor monitorado deve ser positivo.")
    @NotNull(message = "O valor monitorado não pode estar vazio.")
    @Column(nullable = false)
    private BigDecimal valor;

    @Column(name = "data_monitoramento", nullable = false)
    @NotNull(message = "A data do monitoramento é obrigatória.")
    private LocalDate dataMonitoramento;

    @Column(name = "hora_monitoramento", nullable = false)
    @NotNull(message = "A hora do monitoramento é obrigatória.")
    private LocalTime horaMonitoramento;

    // Getters e Setters
    public Integer getIdMonitoramento() {
        return idMonitoramento;
    }

    public void setIdMonitoramento(Integer idMonitoramento) {
        this.idMonitoramento = idMonitoramento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public DiaHoraSessao getDiaHoraSessao() {
        return diaHoraSessao;
    }

    public void setDiaHoraSessao(DiaHoraSessao diaHoraSessao) {
        this.diaHoraSessao = diaHoraSessao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
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
