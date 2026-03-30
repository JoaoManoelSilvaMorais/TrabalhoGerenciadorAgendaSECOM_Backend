package com.br.ueg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.ueg.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

}
