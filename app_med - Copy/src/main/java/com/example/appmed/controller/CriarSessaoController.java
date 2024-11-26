package com.example.appmed.controller;

import com.example.appmed.dto.CriarSessaoRequestDTO;
import com.example.appmed.dto.CriarSessaoResponseDTO;
import com.example.appmed.service.CriarSessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/sessoes")
public class CriarSessaoController {

    @Autowired
    private CriarSessaoService criarSessaoService;

    @PostMapping
    public ResponseEntity<CriarSessaoResponseDTO> criarSessao(@Valid @RequestBody CriarSessaoRequestDTO requestDTO) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String emailUsuario = principal.getUsername();

        CriarSessaoResponseDTO response = criarSessaoService.criarSessao(emailUsuario, requestDTO);
        return ResponseEntity.status(201).body(response); // Retorna 201 Created
    }

    @GetMapping
    public ResponseEntity<List<CriarSessaoResponseDTO>> listarSessoesDoUsuario() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String emailUsuario = principal.getUsername();

        List<CriarSessaoResponseDTO> sessoes = criarSessaoService.listarSessoesDoUsuario(emailUsuario);
        return ResponseEntity.ok(sessoes);
    }
}
