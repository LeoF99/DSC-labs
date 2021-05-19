package com.ufpb.lab3.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufpb.lab3.entidades.Disciplina;

@Repository
public interface DisciplinaRepositorio extends JpaRepository<Disciplina, Long>{
	
}
