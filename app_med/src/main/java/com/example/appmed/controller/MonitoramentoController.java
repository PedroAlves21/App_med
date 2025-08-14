package com.example.appmed.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.appmed.dto.SessaoMonitoramentoDTO;
import com.example.appmed.model.Monitoramento;
import com.example.appmed.service.MonitoramentoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/monitoramentos")
public class MonitoramentoController {

    @Autowired
    private MonitoramentoService monitoramentoService;

    // Iniciar sessões de monitoramento para múltiplos tipos de sessão
    @PostMapping("/iniciar")
    public List<Monitoramento> iniciarSessao(@RequestBody SessaoMonitoramentoDTO sessaoDto) {
        Long userId = getAuthenticatedUserId();
        return monitoramentoService.iniciarSessao(sessaoDto, userId);
    }

    // Finalizar uma sessão de monitoramento existente
    @PutMapping("/finalizar/{id}")
    public Monitoramento finalizarSessao(@PathVariable Long id, @RequestParam(required = false) LocalDateTime dataFim) {
        Long userId = getAuthenticatedUserId();
        return monitoramentoService.finalizarSessao(id, userId, dataFim);
    }

    // Listar sessões por Tipo de Sessão e Usuário
    @GetMapping("/listar")
    public List<Monitoramento> listarSessoes(@RequestParam Long tipoSessaoId) {
        Long userId = getAuthenticatedUserId();
        return monitoramentoService.listarSessoes(userId, tipoSessaoId);
    }

    // Método auxiliar para obter o userId autenticado a partir do token JWT
    private Long getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof String) {
            return Long.valueOf((String) authentication.getPrincipal());
        } else {
            throw new IllegalArgumentException("Usuário não autenticado.");
        }
    }
}

