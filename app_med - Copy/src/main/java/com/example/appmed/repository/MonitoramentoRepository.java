package com.example.appmed.repository;

import com.example.appmed.model.DiaHoraSessao;
import com.example.appmed.model.Monitoramento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface MonitoramentoRepository extends JpaRepository<Monitoramento, Integer> {

    boolean existsByDiaHoraSessaoAndDataMonitoramentoAndHoraMonitoramento(
        DiaHoraSessao diaHoraSessao, LocalDate dataMonitoramento, LocalTime horaMonitoramento
    );
    boolean existsByDiaHoraSessaoAndDataMonitoramento(DiaHoraSessao diaHoraSessao, LocalDate dataMonitoramento);

    List<Monitoramento> findByUsuario_IdUsuarioAndDiaHoraSessao_CriarSessao_IdCriarSessaoAndDataHoraBetween(
            Long usuarioId, Long sessaoId, LocalDateTime inicio, LocalDateTime fim);

    List<Monitoramento> findByUsuario_IdUsuarioAndDiaHoraSessao_CriarSessao_IdCriarSessao(Long usuarioId, Long sessaoId);

    List<Monitoramento> findByUsuario_IdUsuarioAndDataHoraBetween(Long usuarioId, LocalDateTime inicio, LocalDateTime fim);

    List<Monitoramento> findByUsuario_IdUsuario(Long usuarioId);
}
