package com.ufpb.lab3.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufpb.lab3.entidades.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{

}
