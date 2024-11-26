package com.example.appmed.service;

import com.example.appmed.dto.CriarSessaoRequestDTO;
import com.example.appmed.dto.CriarSessaoResponseDTO;
import com.example.appmed.dto.DiaHoraSessaoDTO;
import com.example.appmed.model.CriarSessao;
import com.example.appmed.model.DiaHoraSessao;
import com.example.appmed.model.Usuario;
import com.example.appmed.model.TipoSessao;
import com.example.appmed.repository.CriarSessaoRepository;
import com.example.appmed.repository.DiaHoraSessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CriarSessaoService {

    @Autowired
    private CriarSessaoRepository criarSessaoRepository;

    @Autowired
    private DiaHoraSessaoRepository diaHoraSessaoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public CriarSessaoResponseDTO criarSessao(String emailUsuario, CriarSessaoRequestDTO requestDTO) {
        Usuario usuario = usuarioService.obterUsuarioPorEmail(emailUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    
        TipoSessao tipoSessao = new TipoSessao();
        tipoSessao.setIdTipoSessao(requestDTO.getTipoSessaoId());
    
        CriarSessao novaSessao = new CriarSessao();
        novaSessao.setUsuario(usuario);
        novaSessao.setTipoSessao(tipoSessao);
        novaSessao.setDataFinal(requestDTO.getDataFinal());
    
        if (requestDTO.getQuantidadeInputsPorDia() <= 0) {
            throw new IllegalArgumentException("A quantidade de inputs por dia deve ser maior que zero.");
        }
    
        novaSessao.setQuantidadeInputsPorDia(requestDTO.getQuantidadeInputsPorDia());
        CriarSessao sessaoSalva = criarSessaoRepository.save(novaSessao);
    
        List<DiaHoraSessao> diaHoraSessaoList = requestDTO.getHorarios().stream()
                .flatMap(horario -> requestDTO.getDiasSemana().stream().map(dia -> {
                    DayOfWeek diaSemana = converterDiaSemana(dia);
    
                    DiaHoraSessao diaHoraSessao = new DiaHoraSessao();
                    diaHoraSessao.setCriarSessao(sessaoSalva);
                    diaHoraSessao.setHora(horario);
                    diaHoraSessao.setDiaSemana(diaSemana);
                    return diaHoraSessao;
                }))
                .collect(Collectors.toList());
    
        diaHoraSessaoRepository.saveAll(diaHoraSessaoList);
    
        // Aqui, não chamamos monitoramentoService.criarMonitoramentos para evitar criar valores de monitoramento
    
        return montarResposta(sessaoSalva, diaHoraSessaoList);
    }
    

    public List<CriarSessaoResponseDTO> listarSessoesDoUsuario(String emailUsuario) {
        Usuario usuario = usuarioService.obterUsuarioPorEmail(emailUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        return criarSessaoRepository.findByUsuario(usuario).stream()
                .map(sessao -> montarResposta(sessao, sessao.getDiasHorasSessao()))
                .collect(Collectors.toList());
    }

    private CriarSessaoResponseDTO montarResposta(CriarSessao sessao, List<DiaHoraSessao> diasHorasSessao) {
        List<DiaHoraSessaoDTO> diasHorasSessaoDTO = diasHorasSessao.stream()
                .map(diaHoraSessao -> new DiaHoraSessaoDTO(
                        diaHoraSessao.getIdDiaHoraSessao().longValue(),
                        diaHoraSessao.getDiaSemana(),
                        diaHoraSessao.getHora(),
                        diaHoraSessao.getIdDiaHoraSessao().longValue() // inputId como ID do DiaHoraSessao
                ))
                .collect(Collectors.toList());
    
        return new CriarSessaoResponseDTO(
                sessao.getIdCriarSessao(),
                sessao.getTipoSessao().getIdTipoSessao(),
                sessao.getTipoSessao().getNome(),
                sessao.getTipoSessao().getStatus().name(),
                sessao.getDataFinal(),
                sessao.getQuantidadeInputsPorDia(),
                diasHorasSessaoDTO
        );
    }
    
    

    private DayOfWeek converterDiaSemana(String dia) {
        switch (dia.trim().toLowerCase()) {
            case "segunda":
                return DayOfWeek.MONDAY;
            case "terca":
                return DayOfWeek.TUESDAY;
            case "quarta":
                return DayOfWeek.WEDNESDAY;
            case "quinta":
                return DayOfWeek.THURSDAY;
            case "sexta":
                return DayOfWeek.FRIDAY;
            case "sabado":
                return DayOfWeek.SATURDAY;
            case "domingo":
                return DayOfWeek.SUNDAY;
            default:
                throw new IllegalArgumentException("Dia da semana inválido: " + dia);
        }
    }
}
