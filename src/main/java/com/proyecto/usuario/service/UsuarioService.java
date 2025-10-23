package com.proyecto.usuario.service;

import java.util.List;

import com.proyecto.usuario.entidades.Usuario;

import com.proyecto.usuario.dto.UsuarioDTO;

public interface UsuarioService {

	
	public Usuario BuscarUsuarioPorDNI (String DNI) throws Exception;
	
	public Usuario BuscarUsuarioPorCorreo (String correo) throws Exception;
	
	public List<Usuario> listado () throws Exception;
	
	public void eliminar (int id) throws Exception;
	
	
	
	
}
