package com.example.appmed.repository;

import com.example.appmed.model.TipoSessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoSessaoRepository extends JpaRepository<TipoSessao, Long> {

    // Método para buscar o tipo de sessão pelo nome
    Optional<TipoSessao> findByNome(String nome);

    // Se precisar, adicione o existsByNome também:
    boolean existsByNome(String nome);
}
