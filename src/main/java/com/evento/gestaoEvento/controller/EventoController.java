package com.evento.gestaoEvento.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.evento.gestaoEvento.model.Evento;
import com.evento.gestaoEvento.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository er;
	
	@RequestMapping("/")
	public String getInit() {
		return "redirect:/listar";
	}
	
	@GetMapping("/listar")
	public @ResponseBody ArrayList<Evento> getDados(){
		ArrayList<Evento> retornoEventos =  new ArrayList<>();
		Iterable<Evento> eventos = er.findAll();
		for (Evento evento : eventos) {
			retornoEventos.add(evento);
		}
		return retornoEventos;		
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<?> cadastraConsulta(@RequestBody @Valid Evento evento){
		System.out.println("Evento >>: " + evento);
		er.save(evento);
		return ResponseEntity.ok(evento);
	}
	
	@DeleteMapping("/")
	public ResponseEntity<?> deletarEvento(@RequestBody Evento evento) {
		System.out.println("ID Evento >>: " + evento.getId());
		er.delete(evento);
		return ResponseEntity.ok(evento);
	}
	
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public ResponseEntity<?> updateEvento(@RequestBody Evento evento) {
		Evento update = er.findById(evento.getId());
		System.out.println("Update >>:" + update);
		update.setLocal(evento.getLocal());
		update.setNome(evento.getNome());
		er.save(update);
		return ResponseEntity.ok(er.findById(evento.getId()));
	}

}
