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

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping("/evento")
    public List<Evento> getAllEventos() {
        return eventoRepository.findAll(Sort.by(Sort.Direction.ASC, "dataHoraInicio"));
    }

    @GetMapping("/evento/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
        Evento evento = eventoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com id: " + id));
        return ResponseEntity.ok(evento);
    }

    @PostMapping("/evento")
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento) {
        Evento eventoSalvo = eventoRepository.save(evento);
        return ResponseEntity.ok(eventoSalvo);
    }

    @DeleteMapping("/evento/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        Evento evento = eventoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com id: " + id));
        
        eventoRepository.delete(evento);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/evento/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable Long id, @RequestBody Evento eventoDetails) {
        Evento evento = eventoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com id: " + id));

        evento.setTitulo(eventoDetails.getTitulo());
        evento.setDataHoraInicio(eventoDetails.getDataHoraInicio());
        evento.setDataHoraFinal(eventoDetails.getDataHoraFinal());
        evento.setRequerDeslocamento(eventoDetails.getRequerDeslocamento());
        evento.setProfissionais(eventoDetails.getProfissionais());
        evento.setEquipamentos(eventoDetails.getEquipamentos());
        Evento eventoAtualizado = eventoRepository.save(evento);
        
        return ResponseEntity.ok(eventoAtualizado);
    }
}