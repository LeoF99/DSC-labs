package com.ufpb.lab2.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;


import com.ufpb.lab2.entidades.Disciplina;
import com.ufpb.lab2.dtos.NotaDTO;
import com.ufpb.lab2.dtos.comentarioDTO;
import com.ufpb.lab2.servicos.DisciplinaService;

@RequestMapping("/disciplinas")
@RestController
public class DisciplinasController {
	@Autowired
	private DisciplinaService disciplinaService;
	
	public DisciplinasController(DisciplinaService disciplinaService) {
		super();
		this.disciplinaService = disciplinaService;
	}
	
	@GetMapping("")
	private ResponseEntity<List<Disciplina>> getDisciplinas() {
		return new ResponseEntity<List<Disciplina>>(this.disciplinaService.getDisciplinas(), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	private ResponseEntity<Disciplina> getDisciplinaPorId(@PathVariable Long id) {
		try {
			Disciplina disciplina = this.disciplinaService.getDisciplinaPorId(id);
			
			return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);
		} catch(HttpClientErrorException e) {
			return new ResponseEntity<Disciplina>(e.getStatusCode());
		}
		
	}
	
	@PatchMapping("likes/{id}")
	private ResponseEntity<Disciplina> incrementaLikePorId(@PathVariable Long id) {
		try {
			Disciplina disciplina = this.disciplinaService.incrementaLikePorId(id);
			
			return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);
		} catch (HttpClientErrorException e) {
			return new ResponseEntity<Disciplina>(e.getStatusCode());
		}
	}
	
	@PostMapping("nota/{id}")
	private ResponseEntity<Disciplina> atualizaNota(@PathVariable Long id, @RequestBody NotaDTO nota) {
		try {
			Disciplina disciplina = this.disciplinaService.atualizaNota(id, nota);
			
			return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);
		} catch (HttpClientErrorException e) {
			return new ResponseEntity<Disciplina>(e.getStatusCode());
		}
	}
	
	
	@PostMapping("comentarios/{id}")
	private ResponseEntity<Disciplina> adicionaComentarioPorId(@PathVariable Long id, @RequestBody comentarioDTO comentario) {
		try {
			Disciplina disciplina = this.disciplinaService.adicionaComentarioPorId(id, comentario);
			
			return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);
		} catch (HttpClientErrorException e) {
			return new ResponseEntity<Disciplina>(e.getStatusCode());
		}
	}
	
	@GetMapping("ranking/notas")
	private ResponseEntity<List<Disciplina>> getPeloRankingNotas() {
		return new ResponseEntity<List<Disciplina>>(this.disciplinaService.getPeloRankingNotas(), HttpStatus.OK);
	}
	
	@GetMapping("ranking/likes")
	private ResponseEntity<List<Disciplina>> getPeloRankingLikes() {
		return new ResponseEntity<List<Disciplina>>(this.disciplinaService.getPeloRankingLikes(), HttpStatus.OK);
	}
}
