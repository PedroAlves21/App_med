package com.example.appmed.model;

import jakarta.persistence.*;

@Entity
@Table(name = "criar_sessao")
public class CriarSessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCriarSessao")
    private Long idCriarSessao;

    @ManyToOne
    @JoinColumn(name = "frkTipoSessao", nullable = false) // Verifique se o banco utiliza exatamente "frkTipoSessao"
    private TipoSessao tipoSessao;


    @ManyToOne
    @JoinColumn(name = "frkUsuario", nullable = false)
    private Usuario usuario;

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
}
