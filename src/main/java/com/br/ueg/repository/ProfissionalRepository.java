package com.br.ueg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.ueg.model.Profissional;

// Repositório da entidade Profissional.
// Ao estender JpaRepository, recebe métodos prontos de CRUD, ordenação e paginação.
// O primeiro tipo genérico é a entidade e o segundo representa o tipo da chave primária.
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

}
