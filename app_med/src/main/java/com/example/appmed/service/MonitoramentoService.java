package com.example.appmed.service;

import com.example.appmed.dto.SessaoMonitoramentoDTO;
import com.example.appmed.model.Monitoramento;
import com.example.appmed.model.TipoSessao;
import com.example.appmed.model.Usuario;
import com.example.appmed.repository.MonitoramentoRepository;
import com.example.appmed.repository.TipoSessaoRepository;
import com.example.appmed.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MonitoramentoService {

    @Autowired
    private MonitoramentoRepository monitoramentoRepository;

    @Autowired
    private TipoSessaoRepository tipoSessaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Monitoramento> iniciarSessao(SessaoMonitoramentoDTO sessaoDto, Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("Usuário não autenticado.");
        }

        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        List<Monitoramento> monitoramentos = new ArrayList<>();
        for (Long tipoSessaoId : sessaoDto.getTipoSessaoIds()) {
            TipoSessao tipoSessao = tipoSessaoRepository.findById(tipoSessaoId)
                    .orElseThrow(() -> new IllegalArgumentException("Tipo de Sessão não encontrado"));

            Monitoramento monitoramento = new Monitoramento();
            monitoramento.setDataInicio(sessaoDto.getDataInicio() != null ? sessaoDto.getDataInicio() : LocalDateTime.now());
            monitoramento.setDataHora(LocalDateTime.now());
            monitoramento.setStatus(Monitoramento.Status.ATIVO);
            monitoramento.setValor(sessaoDto.getValor());
            monitoramento.setTipoSessao(tipoSessao);
            monitoramento.setUsuario(usuario); // Define o usuário
            monitoramentos.add(monitoramentoRepository.save(monitoramento));
        }
        return monitoramentos;
    }

    public Monitoramento finalizarSessao(Long idMonitoramento, Long userId, LocalDateTime dataFim) {
        if (userId == null) {
            throw new IllegalArgumentException("Usuário não autenticado.");
        }

        Monitoramento monitoramento = monitoramentoRepository.findByIdMonitoramentoAndUsuario_IdUsuario(idMonitoramento, userId)
                .orElseThrow(() -> new IllegalArgumentException("Monitoramento não encontrado ou não pertence ao usuário"));

        monitoramento.setDataFim(dataFim != null ? dataFim : LocalDateTime.now());
        monitoramento.setStatus(Monitoramento.Status.FINALIZADO);
        return monitoramentoRepository.save(monitoramento);
    }

    public List<Monitoramento> listarSessoes(Long userId, Long tipoSessaoId) {
        if (userId == null) {
            throw new IllegalArgumentException("Usuário não autenticado.");
        }
        
        return monitoramentoRepository.findByUsuario_IdUsuarioAndTipoSessao_IdTipoSessao(userId, tipoSessaoId);
    }
}
