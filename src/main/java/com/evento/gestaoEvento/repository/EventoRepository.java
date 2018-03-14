package com.evento.gestaoEvento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evento.gestaoEvento.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
	Evento findById(long id);

}
