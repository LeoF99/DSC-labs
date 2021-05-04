package com.ufpb.lab2.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufpb.lab2.entidades.Comentario;

public interface RepositorioComentario extends JpaRepository<Comentario, Long> {

}
