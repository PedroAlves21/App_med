package com.example.appmed.controller;

import com.example.appmed.dto.MonitoramentoRequestDTO;
import com.example.appmed.dto.MonitoramentoResponseDTO;  // Novo DTO de resposta
import com.example.appmed.service.MonitoramentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monitoramento")
public class MonitoramentoController {

    @Autowired
    private MonitoramentoService monitoramentoService;

    @PostMapping
    public ResponseEntity<?> inserirDados(@RequestBody MonitoramentoRequestDTO requestDTO,
                                          @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body("Token JWT não fornecido.");
        }

        String token = authorizationHeader.replace("Bearer ", "");
        try {
            return monitoramentoService.inserirDados(requestDTO, token);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno do servidor: " + e.getMessage());
        }
    }

    @PutMapping("/{monitoramentoId}")
    public ResponseEntity<?> editarMonitoramento(@PathVariable Integer monitoramentoId, @RequestParam double novoValor) {
        try {
            return monitoramentoService.editarMonitoramento(monitoramentoId, novoValor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno do servidor: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> obterMonitoramentos(@RequestHeader(value = "Authorization", required = false) String authorizationHeader,
                                                 @RequestParam(required = false) Long sessaoId,
                                                 @RequestParam(required = false) String dataInicio,
                                                 @RequestParam(required = false) String dataFim) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body("Token JWT não fornecido.");
        }

        String token = authorizationHeader.replace("Bearer ", "");

        try {
            List<MonitoramentoResponseDTO> monitoramentos = monitoramentoService.buscarMonitoramentosPorSessaoEPeriodo(
                    token, sessaoId, dataInicio, dataFim);

            if (monitoramentos.isEmpty()) {
                return ResponseEntity.status(404).body("Nenhum monitoramento encontrado para os critérios especificados.");
            }

            return ResponseEntity.ok(monitoramentos);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno do servidor: " + e.getMessage());
        }
    }
}
