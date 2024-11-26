package com.example.appmed.service;

import com.example.appmed.dto.MonitoramentoRequestDTO;
import com.example.appmed.dto.MonitoramentoResponseDTO; // Import do novo DTO de resposta
import com.example.appmed.model.Monitoramento;
import com.example.appmed.model.Usuario;
import com.example.appmed.model.DiaHoraSessao;
import com.example.appmed.repository.MonitoramentoRepository;
import com.example.appmed.repository.UsuarioRepository;
import com.example.appmed.repository.DiaHoraSessaoRepository;
import com.example.appmed.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonitoramentoService {

    @Autowired
    private MonitoramentoRepository monitoramentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DiaHoraSessaoRepository diaHoraSessaoRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<?> inserirDados(MonitoramentoRequestDTO requestDTO, String token) {
        Long usuarioId = jwtTokenProvider.getUsuarioIdFromToken(token);
        if (usuarioId == null) {
            return ResponseEntity.status(400).body("Usuário não autenticado.");
        }
    
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
    
        DiaHoraSessao diaHoraSessao = diaHoraSessaoRepository.findById(requestDTO.getDiaHoraSessaoId())
                .orElseThrow(() -> new IllegalArgumentException("Configuração de Dia e Hora da Sessão inválida."));
    
        DayOfWeek diaConfigurado = diaHoraSessao.getDiaSemana();
        LocalTime horarioConfigurado = diaHoraSessao.getHora();
    
        if (!requestDTO.getDataMonitoramento().getDayOfWeek().equals(diaConfigurado)) {
            return ResponseEntity.status(400)
                    .body("Data do monitoramento não corresponde ao dia configurado para a sessão: " + diaConfigurado);
        }
    
        if (!requestDTO.getHoraMonitoramento().equals(horarioConfigurado)) {
            return ResponseEntity.status(400)
                    .body("Horário do monitoramento não corresponde ao configurado para a sessão: " + horarioConfigurado);
        }
    
        boolean existeRegistro = monitoramentoRepository.existsByDiaHoraSessaoAndDataMonitoramentoAndHoraMonitoramento(
                diaHoraSessao, requestDTO.getDataMonitoramento(), requestDTO.getHoraMonitoramento());
        if (existeRegistro) {
            return ResponseEntity.status(400).body("Já existe um monitoramento registrado para este horário.");
        }
    
        Monitoramento monitoramento = new Monitoramento();
        monitoramento.setUsuario(usuario);
        monitoramento.setDiaHoraSessao(diaHoraSessao);
        monitoramento.setDataMonitoramento(requestDTO.getDataMonitoramento());
        monitoramento.setHoraMonitoramento(requestDTO.getHoraMonitoramento());
        monitoramento.setDataHora(LocalDateTime.now());
        monitoramento.setValor(BigDecimal.valueOf(requestDTO.getValor()));
    
        monitoramentoRepository.save(monitoramento);
    
        return ResponseEntity.status(201).body("Monitoramento inserido com sucesso.");
    }

    public List<MonitoramentoResponseDTO> buscarMonitoramentosPorSessaoEPeriodo(String token, Long sessaoId, String dataInicio, String dataFim) {
        Long usuarioId = jwtTokenProvider.getUsuarioIdFromToken(token);
        if (usuarioId == null) {
            throw new IllegalArgumentException("Usuário não encontrado no token.");
        }

        LocalDateTime inicio = dataInicio != null ? LocalDate.parse(dataInicio).atStartOfDay() : null;
        LocalDateTime fim = dataFim != null ? LocalDate.parse(dataFim).atTime(23, 59, 59) : null;

        if (inicio != null && fim != null && inicio.isAfter(fim)) {
            throw new IllegalArgumentException("A data de início não pode ser posterior à data de fim.");
        }

        List<Monitoramento> monitoramentos;
        if (sessaoId != null && (inicio != null || fim != null)) {
            monitoramentos = monitoramentoRepository.findByUsuario_IdUsuarioAndDiaHoraSessao_CriarSessao_IdCriarSessaoAndDataHoraBetween(
                    usuarioId, sessaoId, inicio, fim);
        } else if (sessaoId != null) {
            monitoramentos = monitoramentoRepository.findByUsuario_IdUsuarioAndDiaHoraSessao_CriarSessao_IdCriarSessao(usuarioId, sessaoId);
        } else if (inicio != null || fim != null) {
            monitoramentos = monitoramentoRepository.findByUsuario_IdUsuarioAndDataHoraBetween(usuarioId, inicio, fim);
        } else {
            monitoramentos = monitoramentoRepository.findByUsuario_IdUsuario(usuarioId);
        }

        return monitoramentos.stream()
                .map(this::mapToMonitoramentoResponseDTO)
                .collect(Collectors.toList());
    }

    private MonitoramentoResponseDTO mapToMonitoramentoResponseDTO(Monitoramento monitoramento) {
        return new MonitoramentoResponseDTO(
                monitoramento.getIdMonitoramento(),
                monitoramento.getUsuario().getIdUsuario(),
                monitoramento.getDiaHoraSessao().getCriarSessao().getIdCriarSessao(),
                monitoramento.getDataHora(),
                monitoramento.getDataMonitoramento(),
                monitoramento.getHoraMonitoramento(),
                monitoramento.getValor(),
                monitoramento.getDiaHoraSessao().getDiaSemana(),
                monitoramento.getDiaHoraSessao().getHora()
        );
    }

    public ResponseEntity<?> editarMonitoramento(Integer monitoramentoId, double novoValor) {
        Monitoramento monitoramento = monitoramentoRepository.findById(monitoramentoId)
                .orElseThrow(() -> new IllegalArgumentException("Monitoramento não encontrado"));

        if (novoValor <= 0) {
            return ResponseEntity.status(400).body("O valor monitorado deve ser positivo.");
        }

        monitoramento.setValor(BigDecimal.valueOf(novoValor));
        monitoramentoRepository.save(monitoramento);

        return ResponseEntity.ok("Monitoramento atualizado com sucesso.");
    }
}
