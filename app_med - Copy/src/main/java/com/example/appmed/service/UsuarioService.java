package com.example.appmed.service;

import com.example.appmed.dto.UsuarioPerfilDTO;
import com.example.appmed.model.Usuario;
import com.example.appmed.repository.UsuarioRepository;
import com.example.appmed.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Método para registrar um novo usuário
    public void registrar(Usuario usuario) {
        // Verifica se o email já está em uso
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Email já está em uso");
        }

        // Define o tipo de usuário como PACIENTE caso não seja informado
        if (usuario.getTipoUsuario() == null) {
            usuario.setTipoUsuario(Usuario.TipoUsuario.PACIENTE);
        }

        // Criptografa a senha antes de salvar
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        // Salva o usuário no banco de dados
        usuarioRepository.save(usuario);
    }

    // Método para autenticar o usuário e retornar o token JWT
    public String autenticar(String email, String senha) {
        // Verifica se o usuário existe no banco de dados
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Credenciais inválidas"));

        // Verifica se a senha fornecida é correta
        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new IllegalArgumentException("Credenciais inválidas");
        }

        // Gera o token JWT com email, role e id do usuário
        return jwtTokenProvider.generateToken(usuario.getEmail(), usuario.getTipoUsuario().name(), usuario.getIdUsuario());
    }

    // Método para obter o perfil do usuário por email
    public UsuarioPerfilDTO obterPerfilPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        return new UsuarioPerfilDTO(
                usuario.getNome(),
                usuario.getDataNascimento() != null ? usuario.getDataNascimento().toString() : null,
                usuario.getGenero(),
                usuario.getNumeroTelefone()
        );
    }

    // Método para obter o usuário completo por email
    public Optional<Usuario> obterUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email); // Certifique-se de que esse método retorna Optional<Usuario>
    }

    // Método para atualizar o perfil do usuário
    public void atualizarPerfilPorEmail(String email, UsuarioPerfilDTO atualizacoes) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        // Verifica se as atualizações de perfil estão presentes e válidas
        if (atualizacoes.getNumeroTelefone() != null && !atualizacoes.getNumeroTelefone().isEmpty()) {
            usuario.setNumeroTelefone(atualizacoes.getNumeroTelefone());
        }

        if (atualizacoes.getGenero() != null) {
            usuario.setGenero(atualizacoes.getGenero());
        }

        // Salva o usuário com as atualizações
        usuarioRepository.save(usuario);
    }
}
