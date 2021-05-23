package com.ufpb.lab3.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.OneToMany;


@Entity
@Getter @Setter
@Data
public class Usuario {
	@Id
	private String email;
	
	private String nome;
	
	private String senha;
	
	
}
