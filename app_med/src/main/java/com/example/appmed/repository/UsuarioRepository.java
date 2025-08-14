package com.example.appmed.repository;

import com.example.appmed.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email); // Verifica se o email já existe no banco de dados
    Usuario findByEmail(String email);   // Busca o usuário pelo email
}

