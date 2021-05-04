package com.ufpb.lab2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufpb.lab2.entidades.Nota;

public interface RepositorioNota extends JpaRepository<Nota, Long> {

}
