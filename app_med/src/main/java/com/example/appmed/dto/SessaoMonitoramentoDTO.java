package com.example.appmed.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class SessaoMonitoramentoDTO {
    
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim; // Opcional no início da sessão
    private List<Long> tipoSessaoIds; // IDs dos tipos de sessão para múltiplos monitoramentos
    private BigDecimal valor;

    // Getters e Setters
    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public List<Long> getTipoSessaoIds() {
        return tipoSessaoIds;
    }

    public void setTipoSessaoIds(List<Long> tipoSessaoIds) {
        this.tipoSessaoIds = tipoSessaoIds;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
