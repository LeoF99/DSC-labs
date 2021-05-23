package com.ufpb.lab3.controladores;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;


import com.ufpb.lab3.entidades.Disciplina;
import com.ufpb.lab3.entidades.Usuario;
import com.ufpb.lab3.dtos.LoginDTO;
import com.ufpb.lab3.dtos.NotaDTO;
import com.ufpb.lab3.dtos.RespostaDeLogin;
import com.ufpb.lab3.dtos.UsuarioDTO;
import com.ufpb.lab3.dtos.comentarioDTO;
import com.ufpb.lab3.servicos.DisciplinaService;
import com.ufpb.lab3.servicos.UsuarioService;

@RestController
public class UsuariosController {
	@Autowired
	private UsuarioService usuarioService;
	
	public UsuariosController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}
	
	@PostMapping("usuarios")
	private ResponseEntity<UsuarioDTO> adicionaUsuario(@RequestBody Usuario usuario) {
		try {
			UsuarioDTO usuarioCriado = this.usuarioService.adicionaUsuario(usuario);
			
			return new ResponseEntity<UsuarioDTO>(usuarioCriado, HttpStatus.OK);
		} catch (HttpClientErrorException e) {
			return new ResponseEntity<UsuarioDTO>(e.getStatusCode());
		}
	}
	
	@PostMapping("login")
	private ResponseEntity<RespostaDeLogin> login(@RequestBody LoginDTO dados) {
		try {
			RespostaDeLogin resposta = this.usuarioService.login(dados);
			
			return new ResponseEntity<RespostaDeLogin>(resposta, HttpStatus.OK);
		} catch (HttpClientErrorException e) {
			return new ResponseEntity<RespostaDeLogin>(e.getStatusCode());
		}
	}
	
	@DeleteMapping("usuarios/auth/{email}")
	private ResponseEntity<UsuarioDTO> removeUsuario(@PathVariable String email) {
		try {
			UsuarioDTO usuario = this.usuarioService.removeUsuario(email);
			
			return new ResponseEntity<UsuarioDTO>(usuario, HttpStatus.OK);
		} catch (HttpClientErrorException e) {
			return new ResponseEntity<UsuarioDTO>(e.getStatusCode());
		}
	}
}
