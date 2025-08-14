package com.example.appmed.dto;

import com.example.appmed.model.Usuario.Genero;

public class UsuarioPerfilDTO {

    private String nome;
    private String dataNascimento; // String formatada para evitar problemas de data
    private Genero genero;  // Tipo atualizado para Genero
    private String numeroTelefone;

    // Construtor vazio
    public UsuarioPerfilDTO() {}

    // Construtor atualizado para aceitar um tipo Genero
    public UsuarioPerfilDTO(String nome, String dataNascimento, Genero genero, String numeroTelefone) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.numeroTelefone = numeroTelefone;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }
}


