package com.ufpb.lab2.servicos;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;

import com.ufpb.lab2.entidades.Disciplina;
import com.ufpb.lab2.entidades.Nota;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufpb.lab2.dtos.NotaDTO;
import com.ufpb.lab2.dtos.comentarioDTO;
import com.ufpb.lab2.repositorios.DisciplinaRepositorio;
import com.ufpb.lab2.repositorios.RepositorioNota;
import com.ufpb.lab2.entidades.Comentario;

@Service
public class DisciplinaService {
	@Autowired
	private DisciplinaRepositorio repositorioDeDisciplinas;
	@Autowired
	private RepositorioNota repositorioDeNotas;
	
	@PostConstruct
	public void initDisciplinas() {
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Disciplina>> typeReference = new TypeReference<List<Disciplina>>(){};
		InputStream inputStream = ObjectMapper.class.getResourceAsStream("/disciplinas.json");
		
		try {
			List<Disciplina> disciplinas = mapper.readValue(inputStream, typeReference);
			
			this.repositorioDeDisciplinas.saveAll(disciplinas);
			System.out.println("DISCIPLINAS CADASTRADAS CORRETAMENTE");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public DisciplinaService() {
		super();
	}
	
	public List<Disciplina> getDisciplinas() {
		return repositorioDeDisciplinas.findAll();
	}
	
	public Disciplina getDisciplinaPorId(Long id) {
		if(repositorioDeDisciplinas.findById(id).isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		
		return repositorioDeDisciplinas.findById(id).get();
	}
	
	public Disciplina incrementaLikePorId(Long id) {
		if(repositorioDeDisciplinas.findById(id).isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		
		Disciplina disc = repositorioDeDisciplinas.findById(id).get();
		
		if(disc.getLikes() == 10) return disc;
		
		disc.setLikes(disc.getLikes() + 1);
		
		repositorioDeDisciplinas.save(disc);
		
		return disc;
	}
	
	public Disciplina atualizaNota(Long id, NotaDTO nota) {
		if(repositorioDeDisciplinas.findById(id).isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		
		if(nota.getNota() > 10) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		
		Disciplina disc = repositorioDeDisciplinas.findById(id).get();
		
		List<Nota> notas = disc.getNotasAntigas();
		
		if(notas.isEmpty()) {
			Nota novaNota = Nota.builder().nota(nota.getNota()).build();
			
			notas.add(novaNota);
			disc.setNota(nota.getNota());
		} else {
			Nota novaNota = Nota.builder().nota(nota.getNota()).build();
			novaNota.setDisciplinaId(id);
			notas.add(novaNota);
			
            Double soma = 0.0;
            for (Nota n : notas) {
                soma += n.getNota();
            }
            double media = soma / notas.size();
            disc.setNota(media);
		}
		
		repositorioDeNotas.saveAll(notas);
		repositorioDeDisciplinas.save(disc);
		
		return disc;
	}
	
	public Disciplina adicionaComentarioPorId(Long id, comentarioDTO comentario) {
		if(repositorioDeDisciplinas.findById(id).isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		
		Disciplina disc = repositorioDeDisciplinas.findById(id).get();
		
		Comentario novoComentario = Comentario.builder().comentario(comentario.getComentario()).build();
		novoComentario.setDisciplinaId(disc.getId());
		
		List<Comentario> comentariosList = disc.getComentarios();
		comentariosList.add(novoComentario);
		
		disc.setComentarios(comentariosList);
		repositorioDeDisciplinas.save(disc);
		
		return disc;
	}
	
	public List<Disciplina> getPeloRankingNotas() {
		List<Disciplina> disciplinas = repositorioDeDisciplinas.findAll();
		
		Collections.sort(disciplinas, new Comparator<Disciplina>() {
			@Override
			public int compare(Disciplina d1, Disciplina d2) {
				return Double.compare(d2.getNota(), d1.getNota());
			}
		});
		
		return disciplinas;
	}
	
	public List<Disciplina> getPeloRankingLikes() {
		List<Disciplina> disciplinas = repositorioDeDisciplinas.findAll();
		
		Collections.sort(disciplinas, new Comparator<Disciplina>() {
			@Override
			public int compare(Disciplina d1, Disciplina d2) {
				return Double.compare(d2.getLikes(), d1.getLikes());
			}
		});
		
		return disciplinas;
	}
}
