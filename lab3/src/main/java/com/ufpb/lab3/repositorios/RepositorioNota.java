package com.ufpb.lab3.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufpb.lab3.entidades.Nota;

public interface RepositorioNota extends JpaRepository<Nota, Long> {

}