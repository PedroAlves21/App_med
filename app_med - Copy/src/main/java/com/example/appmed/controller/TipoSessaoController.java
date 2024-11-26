package com.example.appmed.controller;

import com.example.appmed.model.TipoSessao;
import com.example.appmed.service.TipoSessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-sessao")
public class TipoSessaoController {

    @Autowired
    private TipoSessaoService tipoSessaoService;

    // Endpoint para listar todos os tipos de sessão
    @GetMapping
    public ResponseEntity<List<TipoSessao>> listarTiposSessao() {
        // Chama o serviço que verifica e cria os tipos de sessão se não existirem
        tipoSessaoService.criarTiposSessaoSeNaoExistir();
        
        // Retorna todos os tipos de sessão no banco de dados
        List<TipoSessao> tiposSessao = tipoSessaoService.listarTodos();
        return ResponseEntity.ok(tiposSessao);
    }

    // Endpoint para buscar um tipo de sessão específico por ID
    @GetMapping("/{id}")
    public ResponseEntity<TipoSessao> buscarTipoSessaoPorId(@PathVariable Long id) {
        return tipoSessaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para buscar um tipo de sessão pelo nome
    @GetMapping("/buscar-por-nome")
    public ResponseEntity<TipoSessao> buscarTipoSessaoPorNome(@RequestParam String nome) {
        return tipoSessaoService.buscarPorNome(nome)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
