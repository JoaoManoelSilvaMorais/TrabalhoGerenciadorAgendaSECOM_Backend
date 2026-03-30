package com.br.ueg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.ueg.exception.ResourceNotFoundException;
import com.br.ueg.model.Profissional;
import com.br.ueg.repository.ProfissionalRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/cprofissionais")
@CrossOrigin(origins = "*")
public class ProfissionalController {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @GetMapping("/profissional")
    public List<Profissional> getAllProfissionais () {
        return profissionalRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }
    
    @GetMapping("/profissional/{id}")
    public Profissional getProfissionalById(@PathVariable Long id) {
        return profissionalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado com id: " + id));
    }

    @PostMapping("/profissional")
    public ResponseEntity<Profissional> createProfissional(@RequestBody Profissional profissional) {
        Profissional savedProfissional = profissionalRepository.save(profissional);
        return ResponseEntity.status(201).body(savedProfissional);
    }

    @DeleteMapping("/profissional/{id}")
    public ResponseEntity<Void> deleteProfissional(@PathVariable Long id) {
        Profissional profissional = profissionalRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado com id: " + id));
        
        profissionalRepository.delete(profissional);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/profissional/{id}")
    public ResponseEntity<Profissional> updateProfissional(@PathVariable Long id, @RequestBody Profissional profissionalDetails) {
        Profissional profissional = profissionalRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado com id: " + id));

        profissional.setNome(profissionalDetails.getNome());
        profissional.setDataAdmissao(profissionalDetails.getDataAdmissao());
        profissional.setIsVideomaker(profissionalDetails.getIsVideomaker());
        profissional.setCargaHorariaSemanal(profissionalDetails.getCargaHorariaSemanal());
        profissional.setEventos(profissionalDetails.getEventos());

        Profissional updatedProfissional = profissionalRepository.save(profissional);
        return ResponseEntity.ok(updatedProfissional);
    }

}
