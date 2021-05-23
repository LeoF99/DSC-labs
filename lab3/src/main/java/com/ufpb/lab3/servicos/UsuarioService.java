package com.ufpb.lab3.servicos;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import com.ufpb.lab3.entidades.Disciplina;
import com.ufpb.lab3.entidades.Nota;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufpb.lab3.dtos.NotaDTO;
import com.ufpb.lab3.dtos.comentarioDTO;
import com.ufpb.lab3.repositorios.DisciplinaRepositorio;
import com.ufpb.lab3.repositorios.RepositorioNota;
import com.ufpb.lab3.entidades.Comentario;
import com.ufpb.lab3.repositorios.UsuarioRepositorio;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.ufpb.lab3.entidades.Usuario;
import com.ufpb.lab3.dtos.UsuarioDTO;
import com.ufpb.lab3.dtos.LoginDTO;
import com.ufpb.lab3.dtos.RespostaDeLogin;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepositorio repositorioDeUsuarios;
	
	private final String TOKEN_KEY = "login do leozito";
	
	public UsuarioDTO adicionaUsuario(Usuario usuario) {
		if(!this.repositorioDeUsuarios.findById(usuario.getEmail()).isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		
		Usuario u = repositorioDeUsuarios.save(usuario);
		
		return new UsuarioDTO(u.getEmail(), u.getNome());
	}
	
	public RespostaDeLogin login(LoginDTO dados) {
		if(this.repositorioDeUsuarios.findById(dados.getEmail()).isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		
		verificaSenha(dados.getEmail(), dados.getSenha());
		
		String token = geraToken(dados.getEmail());
		
		return new RespostaDeLogin(dados.getEmail(), token);
	}
	
	public UsuarioDTO removeUsuario(String email) {
		if(this.repositorioDeUsuarios.findById(email).isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		
		Usuario u = repositorioDeUsuarios.findById(email).get();
		
		this.repositorioDeUsuarios.delete(u);
		
		return new UsuarioDTO(email, u.getNome());
	}
	
	private void verificaSenha(String email, String senha) {
		Usuario u = repositorioDeUsuarios.findById(email).get();
		String senhaCadastrada = u.getSenha();
		
		if(senha == senhaCadastrada) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
		}else {
			return;
		}
	}
	
	private String geraToken(String email) {
		return Jwts.builder().setSubject(email)
				.signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 20 * 60 * 1000)).compact(); // 20 minutos
	}
}
