package com.example.appmed.controller;

import com.example.appmed.dto.UsuarioPerfilDTO;
import com.example.appmed.model.Usuario;
import com.example.appmed.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody Usuario usuario) {
        try {
            usuarioService.registrar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao registrar usuário");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody Map<String, String> loginData) {
        try {
            String email = loginData.get("email");
            String senha = loginData.get("senha");

            String token = usuarioService.autenticar(email, senha);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao realizar login");
        }
    }

    @GetMapping("/perfil")
    public ResponseEntity<UsuarioPerfilDTO> obterPerfilUsuario(Authentication authentication) {
        try {
            String email = authentication.getName();
            UsuarioPerfilDTO usuarioPerfil = usuarioService.obterPerfilPorEmail(email);
            return ResponseEntity.ok(usuarioPerfil);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/perfil")
    public ResponseEntity<?> atualizarPerfil(Authentication authentication, @Valid @RequestBody UsuarioPerfilDTO atualizacoes) {
        try {
            String email = authentication.getName();
            usuarioService.atualizarPerfilPorEmail(email, atualizacoes);
            return ResponseEntity.ok("Perfil atualizado com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar perfil");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUsuario(Authentication authentication) {
        return ResponseEntity.status(HttpStatus.OK).body("Logout realizado com sucesso");
    }
}
