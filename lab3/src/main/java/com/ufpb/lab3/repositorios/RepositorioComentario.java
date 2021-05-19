package com.ufpb.lab3.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufpb.lab3.entidades.Comentario;

public interface RepositorioComentario extends JpaRepository<Comentario, Long> {

}