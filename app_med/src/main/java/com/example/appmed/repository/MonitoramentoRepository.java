package com.example.appmed.repository;

import com.example.appmed.model.Monitoramento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MonitoramentoRepository extends JpaRepository<Monitoramento, Long> {

    // Uso de findByIdMonitoramento para referenciar o ID do monitoramento
    Optional<Monitoramento> findByIdMonitoramentoAndUsuario_IdUsuario(Long idMonitoramento, Long idUsuario);

    // Uso correto de findByUsuario_IdUsuario e TipoSessao_IdTipoSessao
    List<Monitoramento> findByUsuario_IdUsuarioAndTipoSessao_IdTipoSessao(Long idUsuario, Long idTipoSessao);
}

