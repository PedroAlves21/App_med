package com.example.appmed.dto;

import java.time.LocalDate;
import java.util.List;

public class CriarSessaoResponseDTO {

    private Long idCriarSessao;
    private Long tipoSessaoId;
    private String tipoSessaoNome;
    private String tipoSessaoStatus;
    private LocalDate dataFinal;
    private int quantidadeInputsPorDia;
    private List<DiaHoraSessaoDTO> diasHorasSessao;

    // Construtor com os parâmetros corretos
    public CriarSessaoResponseDTO(Long idCriarSessao, Long tipoSessaoId, String tipoSessaoNome, String tipoSessaoStatus, 
                                  LocalDate dataFinal, int quantidadeInputsPorDia, List<DiaHoraSessaoDTO> diasHorasSessao) {
        this.idCriarSessao = idCriarSessao;
        this.tipoSessaoId = tipoSessaoId;
        this.tipoSessaoNome = tipoSessaoNome;
        this.tipoSessaoStatus = tipoSessaoStatus;
        this.dataFinal = dataFinal;
        this.quantidadeInputsPorDia = quantidadeInputsPorDia;
        this.diasHorasSessao = diasHorasSessao;
    }

    // Getters e Setters
    public Long getIdCriarSessao() {
        return idCriarSessao;
    }

    public void setIdCriarSessao(Long idCriarSessao) {
        this.idCriarSessao = idCriarSessao;
    }

    public Long getTipoSessaoId() {
        return tipoSessaoId;
    }

    public void setTipoSessaoId(Long tipoSessaoId) {
        this.tipoSessaoId = tipoSessaoId;
    }

    public String getTipoSessaoNome() {
        return tipoSessaoNome;
    }

    public void setTipoSessaoNome(String tipoSessaoNome) {
        this.tipoSessaoNome = tipoSessaoNome;
    }

    public String getTipoSessaoStatus() {
        return tipoSessaoStatus;
    }

    public void setTipoSessaoStatus(String tipoSessaoStatus) {
        this.tipoSessaoStatus = tipoSessaoStatus;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public int getQuantidadeInputsPorDia() {
        return quantidadeInputsPorDia;
    }

    public void setQuantidadeInputsPorDia(int quantidadeInputsPorDia) {
        this.quantidadeInputsPorDia = quantidadeInputsPorDia;
    }

    public List<DiaHoraSessaoDTO> getDiasHorasSessao() {
        return diasHorasSessao;
    }

    public void setDiasHorasSessao(List<DiaHoraSessaoDTO> diasHorasSessao) {
        this.diasHorasSessao = diasHorasSessao;
    }
}
