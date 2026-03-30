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

    // Injeta o repositório para manipular os dados da entidade Profissional.
    @Autowired
    private ProfissionalRepository profissionalRepository;

    // Endpoint GET que retorna todos os profissionais ordenados pelo nome.
    @GetMapping("/profissional")
    public List<Profissional> getAllProfissionais () {
        return profissionalRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }
    
    // Endpoint GET que busca um profissional pelo id informado na URL.
    // Lança ResourceNotFoundException quando o registro não é encontrado.
    @GetMapping("/profissional/{id}")
    public Profissional getProfissionalById(@PathVariable Long id) {
        return profissionalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado com id: " + id));
    }

    // Endpoint POST que cria um novo profissional com os dados recebidos no corpo da requisição.
    // Retorna HTTP 201 (Created) com o objeto persistido.
    @PostMapping("/profissional")
    public ResponseEntity<Profissional> createProfissional(@RequestBody Profissional profissional) {
        Profissional savedProfissional = profissionalRepository.save(profissional);
        return ResponseEntity.status(201).body(savedProfissional);
    }

    // Endpoint DELETE que remove um profissional pelo id após validar se ele existe.
    @DeleteMapping("/profissional/{id}")
    public ResponseEntity<Void> deleteProfissional(@PathVariable Long id) {
        Profissional profissional = profissionalRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado com id: " + id));
        
        profissionalRepository.delete(profissional);
        // Retorna HTTP 204 (No Content) para indicar exclusão sem corpo na resposta.
        return ResponseEntity.noContent().build();
    }

    // Endpoint PUT que atualiza os dados de um profissional existente.
    // Busca o registro atual, aplica os novos valores e salva novamente no banco.
    @PutMapping("/profissional/{id}")
    public ResponseEntity<Profissional> updateProfissional(@PathVariable Long id, @RequestBody Profissional profissionalDetails) {
        Profissional profissional = profissionalRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado com id: " + id));

        // Atualiza os campos da entidade com os valores enviados na requisição.
        profissional.setNome(profissionalDetails.getNome());
        profissional.setDataAdmissao(profissionalDetails.getDataAdmissao());
        profissional.setIsVideomaker(profissionalDetails.getIsVideomaker());
        profissional.setCargaHorariaSemanal(profissionalDetails.getCargaHorariaSemanal());
        profissional.setEventos(profissionalDetails.getEventos());

        Profissional updatedProfissional = profissionalRepository.save(profissional);
        // Retorna HTTP 200 (OK) com o profissional atualizado.
        return ResponseEntity.ok(updatedProfissional);
    }

}
