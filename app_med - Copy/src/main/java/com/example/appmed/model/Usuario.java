package com.example.appmed.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")  
    private Long idUsuario;

    @NotBlank(message = "Nome é obrigatório.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    @Column(name = "nome", nullable = false)
    private String nome;

    @Email(message = "Formato de e-mail inválido.")
    @NotBlank(message = "E-mail é obrigatório.")
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @NotBlank(message = "Senha é obrigatória.")
    @Size(min = 6, max = 256, message = "A senha deve ter entre 6 e 256 caracteres.")
    @Column(name = "senha", nullable = false)
    private String senha;

    @Past(message = "A data de nascimento deve ser uma data passada.")
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero", nullable = false)
    private Genero genero = Genero.MASCULINO;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario = TipoUsuario.PACIENTE;

    @Pattern(regexp = "^\\+?[0-9]*$", message = "O número de telefone deve conter apenas dígitos e pode começar com '+'.")
    @Size(max = 15, message = "O número de telefone deve ter no máximo 15 caracteres.")
    @Column(name = "numero_telefone", nullable = true)
    private String numeroTelefone;

    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Monitoramento> monitoramentos;

    // Enumeração para Gênero
    public enum Genero {
        MASCULINO("Masculino"),
        FEMININO("Feminino");

        private final String valor;

        Genero(String valor) {
            this.valor = valor;
        }

        @JsonValue
        public String getValor() {
            return valor;
        }

        @JsonCreator
        public static Genero fromValue(String valor) {
            for (Genero genero : Genero.values()) {
                if (genero.valor.equalsIgnoreCase(valor)) {
                    return genero;
                }
            }
            throw new IllegalArgumentException("Valor inválido para Genero: " + valor);
        }
    }

    // Enumeração para Tipo de Usuário
    public enum TipoUsuario {
        PACIENTE("Paciente"),
        ADMINISTRADOR("Administrador");

        private final String valor;

        TipoUsuario(String valor) {
            this.valor = valor;
        }

        @JsonValue
        public String getValor() {
            return valor;
        }

        @JsonCreator
        public static TipoUsuario fromValue(String valor) {
            for (TipoUsuario tipo : TipoUsuario.values()) {
                if (tipo.valor.equalsIgnoreCase(valor)) {
                    return tipo;
                }
            }
            throw new IllegalArgumentException("Valor inválido para TipoUsuario: " + valor);
        }
    }

    // Métodos de ciclo de vida para controlar timestamps automaticamente
    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        atualizadoEm = LocalDateTime.now();
    }

    // Getters e Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public List<Monitoramento> getMonitoramentos() {
        return monitoramentos;
    }

    public void setMonitoramentos(List<Monitoramento> monitoramentos) {
        this.monitoramentos = monitoramentos;
    }
}
