package com.example.appmed.service;

import com.example.appmed.model.TipoSessao;
import com.example.appmed.repository.TipoSessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoSessaoService {

    @Autowired
    private TipoSessaoRepository tipoSessaoRepository;

    // Método para listar todos os tipos de sessão
    public List<TipoSessao> listarTodos() {
        return tipoSessaoRepository.findAll();
    }

    // Método para buscar um tipo de sessão específico por ID
    public Optional<TipoSessao> buscarPorId(Long id) {
        return tipoSessaoRepository.findById(id);
    }

    // Método para buscar um tipo de sessão pelo nome
    public Optional<TipoSessao> buscarPorNome(String nome) {
        return tipoSessaoRepository.findByNome(nome);
    }

    // Método para criar os tipos de sessão caso não existam
    public void criarTiposSessaoSeNaoExistir() {
        // Verifica se o tipo de sessão "Glicemia" existe
        if (!tipoSessaoRepository.existsByNome("Glicemia")) {
            tipoSessaoRepository.save(new TipoSessao("Glicemia"));
        }

        // Verifica se o tipo de sessão "Pressão Arterial" existe
        if (!tipoSessaoRepository.existsByNome("Pressão Arterial")) {
            tipoSessaoRepository.save(new TipoSessao("Pressão Arterial"));
        }

        // Verifica se o tipo de sessão "Peso" existe
        if (!tipoSessaoRepository.existsByNome("Peso")) {
            tipoSessaoRepository.save(new TipoSessao("Peso"));
        }
    }
}
