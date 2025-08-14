package com.example.appmed.service;

import com.example.appmed.dto.UsuarioPerfilDTO;
import com.example.appmed.model.Usuario;
import com.example.appmed.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Método para registrar um novo usuário
    public void registrar(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Email já está em uso");
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
    }

    // Método para autenticar o usuário e retornar o objeto Usuario
    public Usuario autenticar(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null && passwordEncoder.matches(senha, usuario.getSenha())) {
            return usuario; // Retorna o usuário se a autenticação for bem-sucedida
        } else {
            throw new IllegalArgumentException("Credenciais inválidas");
        }
    }

    // Método para obter perfil do usuário por ID
    public UsuarioPerfilDTO obterPerfilPorId(Long id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            return new UsuarioPerfilDTO(
                    usuario.getNome(),
                    usuario.getDataNascimento() != null ? usuario.getDataNascimento().toString() : null,
                    usuario.getGenero(),
                    usuario.getNumeroTelefone()
            );
        } else {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
    }

    // Método para atualizar perfil do usuário
    public void atualizarPerfil(Long id, UsuarioPerfilDTO atualizacoes) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
        
        if (atualizacoes.getNumeroTelefone() != null) {
            usuario.setNumeroTelefone(atualizacoes.getNumeroTelefone());
        }
        if (atualizacoes.getGenero() != null) {
            usuario.setGenero(atualizacoes.getGenero());
        }

        usuarioRepository.save(usuario);
    }
}
