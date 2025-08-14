package com.example.appmed.repository;

import com.example.appmed.model.TipoSessao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoSessaoRepository extends JpaRepository<TipoSessao, Long> {
    // Defina consultas personalizadas aqui, se necessário
}
