package com.proyecto.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.usuario.entidades.Usuario;

import com.proyecto.usuario.dto.UsuarioDTO;

import com.proyecto.usuario.service.UsuarioService;

import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "false")
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
		
		@RequestMapping(value="/listado", method={RequestMethod.GET})
		public ResponseEntity<List<Usuario>> listado() throws Exception{
			  return new ResponseEntity<>(usuarioService.listado(),HttpStatus.OK);
		}
		
		
		@RequestMapping(value="/eliminar/{id}", method={RequestMethod.GET})
		public ResponseEntity<Usuario> eliminarUsuario(@PathVariable("id") int id) throws Exception{
			usuarioService.eliminar(id);
			  return new ResponseEntity<>(HttpStatus.OK);
		}
		
		
	

}
