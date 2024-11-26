package com.example.appmed.repository;
import com.example.appmed.model.CriarSessao;
import com.example.appmed.model.Usuario;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CriarSessaoRepository extends JpaRepository<CriarSessao, Long> {
    // Definindo o método personalizado para buscar sessões pelo usuário
    List<CriarSessao> findByUsuario(Usuario usuario);
}
