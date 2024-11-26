package com.example.appmed.repository;

import com.example.appmed.model.DiaHoraSessao;
import com.example.appmed.model.CriarSessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface DiaHoraSessaoRepository extends JpaRepository<DiaHoraSessao, Long> {
    List<DiaHoraSessao> findByCriarSessaoAndDiaSemana(CriarSessao criarSessao, DayOfWeek diaSemana);
}
