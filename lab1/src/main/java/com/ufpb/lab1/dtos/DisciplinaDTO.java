package com.ufpb.lab1.dtos;

public class DisciplinaDTO {
	private String nome;
	private double nota;
	
	public DisciplinaDTO(String nome, double nota) {
		super();
		this.nome = nome;
		this.nota = nota;
	}

	public String getNome() {
		return nome;
	}

	public double getNota() {
		return nota;
	}
	
}
