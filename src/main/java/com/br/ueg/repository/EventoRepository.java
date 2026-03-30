package com.br.ueg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.ueg.model.Evento;

// Repositório da entidade Evento.
// Ao estender JpaRepository, herda métodos de CRUD, ordenação e paginação.
// O primeiro tipo genérico representa a entidade e o segundo, o tipo da chave primária.
public interface EventoRepository extends JpaRepository<Evento, Long> {

}
