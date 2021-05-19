package com.ufpb.lab3.dtos;

public class DisciplinaDTO {
	private String nome;
	private long id;
	
	public DisciplinaDTO(String nome, long id) {
		super();
		this.nome = nome;
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public long getNota() {
		return id;
	}
	
}
