package com.br.ueg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.ueg.model.Equipamento;

// Repositório da entidade Equipamento.
// Ao estender JpaRepository, herda operações prontas de CRUD e paginação.
// O primeiro tipo genérico é a entidade e o segundo é o tipo da chave primária.
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

    
}

