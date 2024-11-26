package com.example.appmed.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CriarSessaoRequestDTO {

    @NotNull(message = "O tipo de sessão é obrigatório")
    private Long tipoSessaoId;

    @NotNull(message = "A data final é obrigatória")
    @Future(message = "A data final deve ser no futuro")
    private LocalDate dataFinal;

    @NotNull(message = "A quantidade de inputs por dia é obrigatória")
    private Integer quantidadeInputsPorDia;

    @NotNull(message = "Os dias da semana são obrigatórios")
    private List<String> diasSemana;

    @NotNull(message = "Os horários são obrigatórios")
    private List<LocalTime> horarios;

    // Getters e Setters
    public Long getTipoSessaoId() {
        return tipoSessaoId;
    }

    public void setTipoSessaoId(Long tipoSessaoId) {
        this.tipoSessaoId = tipoSessaoId;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Integer getQuantidadeInputsPorDia() {
        return quantidadeInputsPorDia;
    }

    public void setQuantidadeInputsPorDia(Integer quantidadeInputsPorDia) {
        this.quantidadeInputsPorDia = quantidadeInputsPorDia;
    }

    public List<String> getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(List<String> diasSemana) {
        this.diasSemana = diasSemana;
    }

    public List<LocalTime> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<LocalTime> horarios) {
        this.horarios = horarios;
    }
}
