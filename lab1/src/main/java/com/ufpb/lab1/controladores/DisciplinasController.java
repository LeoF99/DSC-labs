package com.ufpb.lab1.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;


import com.ufpb.lab1.entidades.Disciplina;
import com.ufpb.lab1.dtos.DisciplinaDTO;
import com.ufpb.lab1.servicos.DisciplinaService;

@RequestMapping("/disciplinas")
@RestController
public class DisciplinasController {
	@Autowired
	private DisciplinaService disciplinaService;
	
	public DisciplinasController(DisciplinaService disciplinaService) {
		super();
		this.disciplinaService = disciplinaService;
	}

	@PostMapping("")
	private ResponseEntity<Disciplina> criaDisciplina(@RequestBody DisciplinaDTO disciplina) {
		return new ResponseEntity<Disciplina>(this.disciplinaService.criaDisciplina(disciplina), HttpStatus.CREATED);
	}
	
	@GetMapping("")
	private ResponseEntity<List<Disciplina>> getDisciplinas() {
		return new ResponseEntity<List<Disciplina>>(this.disciplinaService.getDisciplinas(), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	private ResponseEntity<Disciplina> getDisciplinaPorId(@PathVariable Integer id) {
		Disciplina disciplina = this.disciplinaService.getDisciplinaPorId(id);
		
		if(disciplina != null) {
			return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);		
		}
		
		return new ResponseEntity<Disciplina>(disciplina, HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("{id}/nome")
	private ResponseEntity<Disciplina> atualizaNomeDisciplina(@PathVariable Integer id, @RequestBody String nome) {
		if(nome.length() == 0) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		Disciplina disciplina = this.disciplinaService.atualizaNomeDisciplina(id, nome);
		
		if(disciplina != null) {
			return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);
		}
		
		return new ResponseEntity<Disciplina>(disciplina, HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("{id}/nota")
	private ResponseEntity<Disciplina> AtualizaNotaDisciplina(@PathVariable Integer id, @RequestBody double nota) {
		if(nota > 10 || nota < 0) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		Disciplina disciplina = this.disciplinaService.atualizaNotaDisciplina(id, nota);
		
		if(disciplina != null) {
			return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);
		}
		
		return new ResponseEntity<Disciplina>(disciplina, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("{id}")
	private ResponseEntity<Disciplina> deletaDisciplina(@PathVariable Integer id) {
		Disciplina disciplina = this.disciplinaService.deletaDisciplina(id);
		
		if(disciplina != null) {
			return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);
		}
		
		return new ResponseEntity<Disciplina>(disciplina, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/ranking")
	private ResponseEntity<List<Disciplina>> getDisciplinasPorRanking() {
		return new ResponseEntity<List<Disciplina>>(this.disciplinaService.getDisciplinasPorRanking(), HttpStatus.OK);
	}
}
