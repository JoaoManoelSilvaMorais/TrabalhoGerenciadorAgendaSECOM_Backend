package com.br.ueg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.br.ueg.model.Evento;
import com.br.ueg.repository.EventoRepository;
import com.br.ueg.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/ceventos")
@CrossOrigin(origins = "*")
public class EventoController {

    // Injeta o repositório para operações de persistência da entidade Evento.
    @Autowired
    private EventoRepository eventoRepository;

    // Endpoint GET que lista todos os eventos ordenados por data/hora de início.
    @GetMapping("/evento")
    public List<Evento> getAllEventos() {
        return eventoRepository.findAll(Sort.by(Sort.Direction.ASC, "dataHoraInicio"));
    }

    // Endpoint GET que busca um evento pelo id informado na URL.
    // Lança ResourceNotFoundException quando o evento não existe.
    @GetMapping("/evento/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
        Evento evento = eventoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com id: " + id));
        // Retorna HTTP 200 (OK) com o evento encontrado.
        return ResponseEntity.ok(evento);
    }

    // Endpoint POST que cria um novo evento com os dados recebidos no corpo da requisição.
    // Retorna o objeto salvo com HTTP 200.
    @PostMapping("/evento")
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento) {
        Evento eventoSalvo = eventoRepository.save(evento);
        return ResponseEntity.ok(eventoSalvo);
    }

    // Endpoint DELETE que remove um evento pelo id após validar sua existência.
    @DeleteMapping("/evento/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        Evento evento = eventoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com id: " + id));
        
        eventoRepository.delete(evento);
        // Retorna HTTP 204 (No Content) para indicar remoção concluída.
        return ResponseEntity.noContent().build();
    }

    // Endpoint PUT que atualiza os dados de um evento existente.
    // Busca o registro atual, atualiza os campos e persiste novamente.
    @PutMapping("/evento/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable Long id, @RequestBody Evento eventoDetails) {
        Evento evento = eventoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com id: " + id));

        // Copia para a entidade existente os valores recebidos na requisição.
        evento.setTitulo(eventoDetails.getTitulo());
        evento.setDataHoraInicio(eventoDetails.getDataHoraInicio());
        evento.setDataHoraFinal(eventoDetails.getDataHoraFinal());
        evento.setRequerDeslocamento(eventoDetails.getRequerDeslocamento());
        evento.setProfissionais(eventoDetails.getProfissionais());
        evento.setEquipamentos(eventoDetails.getEquipamentos());
        Evento eventoAtualizado = eventoRepository.save(evento);
        
        // Retorna HTTP 200 (OK) com o evento atualizado.
        return ResponseEntity.ok(eventoAtualizado);
    }
}