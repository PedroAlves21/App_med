package com.example.appmed.repository;

import com.example.appmed.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Verifica se o email já existe no banco de dados
    boolean existsByEmail(String email);
    
    // Busca o usuário pelo email
    Optional<Usuario> findByEmail(String email);

    // Busca o usuário pelo ID, com @NonNull para garantir nulidade correta
    @NonNull
    Optional<Usuario> findById(@NonNull Long id);
}
