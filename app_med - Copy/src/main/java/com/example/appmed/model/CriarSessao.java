package com.example.appmed.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "criar_sessao")
public class CriarSessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_criar_sessao")
    private Long idCriarSessao;

    @ManyToOne
    @JoinColumn(name = "tipo_sessao_id", nullable = false)
    private TipoSessao tipoSessao;

    @ManyToOne
    @JoinColumn(name = "fk_usuario", nullable = false)
    @NotNull(message = "O usuário é obrigatório.")
    private Usuario usuario;

    @Column(name = "data_final", nullable = false)
    @NotNull(message = "A data final é obrigatória.")
    private LocalDate dataFinal;

    @Column(name = "quantidade_inputs_por_dia", nullable = false)
    @NotNull(message = "A quantidade de inputs por dia é obrigatória.")
    private int quantidadeInputsPorDia;

    @OneToMany(mappedBy = "criarSessao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DiaHoraSessao> diasHorasSessao;

    // Construtor vazio para JPA
    public CriarSessao() {
    }

    // Construtor completo
    public CriarSessao(TipoSessao tipoSessao, Usuario usuario, LocalDate dataFinal, int quantidadeInputsPorDia, List<DiaHoraSessao> diasHorasSessao) {
        this.tipoSessao = tipoSessao;
        this.usuario = usuario;
        this.dataFinal = dataFinal;
        this.quantidadeInputsPorDia = quantidadeInputsPorDia;
        this.diasHorasSessao = diasHorasSessao;
    }

    // Método para verificar se a sessão está ativa com base na data final
    public boolean isAtiva() {
        return LocalDate.now().isBefore(dataFinal);
    }

    // Getters e Setters
    public Long getIdCriarSessao() {
        return idCriarSessao;
    }

    public void setIdCriarSessao(Long idCriarSessao) {
        this.idCriarSessao = idCriarSessao;
    }

    public TipoSessao getTipoSessao() {
        return tipoSessao;
    }

    public void setTipoSessao(TipoSessao tipoSessao) {
        this.tipoSessao = tipoSessao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public List<DiaHoraSessao> getDiasHorasSessao() {
        return diasHorasSessao;
    }

    public void setDiasHorasSessao(List<DiaHoraSessao> diasHorasSessao) {
        this.diasHorasSessao = diasHorasSessao;
    }

    @Override
    public String toString() {
        return "CriarSessao{" +
                "idCriarSessao=" + idCriarSessao +
                ", tipoSessao=" + tipoSessao +
                ", usuario=" + usuario +
                ", dataFinal=" + dataFinal +
                ", quantidadeInputsPorDia=" + quantidadeInputsPorDia +
                ", diasHorasSessao=" + diasHorasSessao +
                '}';
    }
}
