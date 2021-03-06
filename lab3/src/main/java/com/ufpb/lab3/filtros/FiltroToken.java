package com.ufpb.lab3.filtros;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.PrematureJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class FiltroToken extends GenericFilterBean {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		String header = req.getHeader("Authorization");

		if (header == null) {
			throw new ServletException("Token inexistente ou mal formatado!");
		}

		try {
			Jwts.parser().setSigningKey("login do leozito").parseClaimsJws(header).getBody();
		} catch(SignatureException | ExpiredJwtException | MalformedJwtException | PrematureJwtException | UnsupportedJwtException | IllegalArgumentException e) {
			
			//aqui optamos por tratar todas as exceções que podem ser lançadas da mesma forma e simplesmente
			//repassar a mensagem de erro
			//se quiser enviar mensagens em portugues mais personalizadas teria que capturar exceção
			//por exceção
           		((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            		
			return;//a requisição nem precisa passar adiante, retornar já para o cliente pois não pode prosseguir 
			//daqui pra frente por falta de autorização
        }

		chain.doFilter(request, response);
	}
}
