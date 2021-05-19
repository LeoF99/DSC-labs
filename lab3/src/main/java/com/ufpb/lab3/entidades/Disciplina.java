package com.ufpb.lab3.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.OneToMany;

@Entity
@Getter @Setter
@Data
public class Disciplina {
	@Id @GeneratedValue @Getter
	private long id;
	
	private String nome;
	
	private double nota;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Comentario> comentarios = new ArrayList<Comentario>();
	
	private int likes;
	
	@OneToMany(cascade = CascadeType.ALL)
    private List<Nota> notasAntigas = new ArrayList<Nota>();
}
