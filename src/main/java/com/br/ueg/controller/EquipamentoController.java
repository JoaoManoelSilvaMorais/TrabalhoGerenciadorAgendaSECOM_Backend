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

    // Injeta o repositório para acesso ao banco de dados da entidade Equipamento.
    @Autowired
    private EquipamentoRepository equipamentoRepository;

    // Endpoint GET que retorna todos os equipamentos ordenados por id (crescente).
    @GetMapping("/equipamento")
    public List<Equipamento> getAllEquipamentos(){
        return equipamentoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    // Endpoint GET que busca um equipamento pelo id informado na URL.
    // Lança ResourceNotFoundException caso o id não exista.
    @GetMapping("/equipamento/{id}")
    public Equipamento getEquipamentoById(@PathVariable Long id) {
        return equipamentoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Equipamento não encontrado com id: " + id));
    }

    // Endpoint POST que cria um novo equipamento com os dados enviados no corpo da requisição.
    // Retorna HTTP 201 (Created) com o objeto salvo.
    @PostMapping("/equipamento")
    public ResponseEntity<Equipamento> createEquipamento(@RequestBody Equipamento equipamento) {
        Equipamento savedEquipamento = equipamentoRepository.save(equipamento);
        return ResponseEntity.status(201).body(savedEquipamento);
    }

    // Endpoint DELETE que remove um equipamento pelo id.
    // Primeiro verifica se o registro existe para evitar exclusão inválida.
    @DeleteMapping("/equipamento/{id}")
    public ResponseEntity<Void> deleteEquipamento(@PathVariable Long id) {
        Equipamento equipamento = equipamentoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Equipamento não encontrado com id: " + id));
        
        equipamentoRepository.delete(equipamento);
        // Retorna HTTP 204 (No Content) indicando exclusão concluída sem corpo na resposta.
        return ResponseEntity.noContent().build();
    }

    // Endpoint PUT que atualiza os dados de um equipamento existente.
    // Busca o registro atual, sobrescreve os campos com os novos valores e salva no banco.
    @PutMapping("/equipamento/{id}")
    public ResponseEntity<Equipamento> updateEquipamento(@PathVariable Long id, @RequestBody Equipamento equipamentoDetails) {
        Equipamento equipamento = equipamentoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Equipamento não encontrado com id: " + id));

        // Atualiza os campos do equipamento com os valores recebidos na requisição.
        equipamento.setModelo(equipamentoDetails.getModelo());
        equipamento.setDataAquisicao(equipamentoDetails.getDataAquisicao());
        equipamento.setIsDisponivel(equipamentoDetails.getIsDisponivel());
        equipamento.setPesoGramas(equipamentoDetails.getPesoGramas());
        equipamento.setEventos(equipamentoDetails.getEventos());

        Equipamento updatedEquipamento = equipamentoRepository.save(equipamento);
        // Retorna HTTP 200 (OK) com o equipamento atualizado.
        return ResponseEntity.ok(updatedEquipamento);
    }

}
