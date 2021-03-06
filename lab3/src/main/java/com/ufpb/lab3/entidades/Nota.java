package com.ufpb.lab3.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(builderClassName = "Builder")
@Entity
public class Nota {
	@Id
    @GeneratedValue()
    private Long id;
	private Long DisciplinaId;
    private Double nota;
}
