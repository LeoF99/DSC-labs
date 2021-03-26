package com.ufpb.lab1.servicos;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.ufpb.lab1.entidades.Disciplina;
import com.ufpb.lab1.dtos.DisciplinaDTO;

@Service
public class DisciplinaService {
	private List<Disciplina> disciplinas;
	static private int identificador = 1;
	
	public DisciplinaService(List<Disciplina> disciplinas) {
		super();
		this.disciplinas = disciplinas;
	}
	
	public Disciplina criaDisciplina(DisciplinaDTO disciplina) {
		Disciplina novaDisciplina = new Disciplina(identificador++, disciplina.getNome(), disciplina.getNota());
		this.disciplinas.add(novaDisciplina);
		return novaDisciplina;
	}
	
	public List<Disciplina> getDisciplinas() {
		return this.disciplinas;
	}
	
	public Disciplina getDisciplinaPorId(int id) {
		for (Disciplina disciplina : this.disciplinas) {
			if (disciplina.getId() == id) {
				return disciplina;
			}
		}
		return null;
	}
	
	public Disciplina atualizaNomeDisciplina(int id, String nome) {
		for (Disciplina disciplina : this.disciplinas) {
			if (disciplina.getId() == id) {
				disciplina.setNome(nome);
				return disciplina;
			}
		}
		return null;
	}
	
	public Disciplina atualizaNotaDisciplina(int id, double nota) {
		for (Disciplina disciplina : this.disciplinas) {
			if (disciplina.getId() == id) {
				disciplina.setNota(nota);
				return disciplina;
			}
		}
		return null;
	}
	
	public Disciplina deletaDisciplina(int id) {
		for (Disciplina disciplina : this.disciplinas) {
			if (disciplina.getId() == id) {
				Disciplina disciplinaDeletada = disciplina;
				this.disciplinas.remove(disciplina);
				return disciplinaDeletada;
			}
		}
		return null;
	}
	
	public List<Disciplina> getDisciplinasPorRanking() {
		List<Disciplina> disciplinas = this.getDisciplinas();
		
		Collections.sort(disciplinas, new Comparator<Disciplina>() {
			@Override
			public int compare(Disciplina d1, Disciplina d2) {
				return Double.compare(d2.getNota(), d1.getNota());
			}
		});
		
		return disciplinas;
	}
}
