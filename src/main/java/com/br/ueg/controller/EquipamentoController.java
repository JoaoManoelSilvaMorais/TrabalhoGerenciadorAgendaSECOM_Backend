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
import com.br.ueg.model.Equipamento;
import com.br.ueg.repository.EquipamentoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/cequipamentos")
@CrossOrigin(origins = "*")
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @GetMapping("/equipamento")
    public List<Equipamento> getAllEquipamentos(){
        return equipamentoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/equipamento/{id}")
    public Equipamento getEquipamentoById(@PathVariable Long id) {
        return equipamentoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Equipamento não encontrado com id: " + id));
    }

    @PostMapping("/equipamento")
    public ResponseEntity<Equipamento> createEquipamento(@RequestBody Equipamento equipamento) {
        Equipamento savedEquipamento = equipamentoRepository.save(equipamento);
        return ResponseEntity.status(201).body(savedEquipamento);
    }

    @DeleteMapping("/equipamento/{id}")
    public ResponseEntity<Void> deleteEquipamento(@PathVariable Long id) {
        Equipamento equipamento = equipamentoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Equipamento não encontrado com id: " + id));
        
        equipamentoRepository.delete(equipamento);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/equipamento/{id}")
    public ResponseEntity<Equipamento> updateEquipamento(@PathVariable Long id, @RequestBody Equipamento equipamentoDetails) {
        Equipamento equipamento = equipamentoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Equipamento não encontrado com id: " + id));

        // Atualiza os campos do equipamento
        equipamento.setModelo(equipamentoDetails.getModelo());
        equipamento.setDataAquisicao(equipamentoDetails.getDataAquisicao());
        equipamento.setIsDisponivel(equipamentoDetails.getIsDisponivel());
        equipamento.setPesoGramas(equipamentoDetails.getPesoGramas());
        equipamento.setEventos(equipamentoDetails.getEventos());

        Equipamento updatedEquipamento = equipamentoRepository.save(equipamento);
        return ResponseEntity.ok(updatedEquipamento);
    }

}
