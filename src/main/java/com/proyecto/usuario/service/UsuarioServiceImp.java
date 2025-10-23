package com.proyecto.usuario.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;  // <-- IMPORTANTE

import com.proyecto.usuario.entidades.Usuario;
import com.proyecto.usuario.repository.UsuarioRepository;
import com.proyecto.usuario.dto.UsuarioDTO;

@Service   // <-- ESTA ANOTACIÓN ES LA CLAVE
public class UsuarioServiceImp implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;


	@Override
	public Usuario BuscarUsuarioPorDNI(String DNI) throws Exception {
		Optional<Usuario> optUsuario = usuarioRepository.findByDNI(DNI);
		return optUsuario.orElseThrow(() -> new Exception ("Usuario no encontrado con DNI " + DNI));
	}

	@Override
	public Usuario BuscarUsuarioPorCorreo(String correo) throws Exception {
		Optional<Usuario> optUsuario = usuarioRepository.findByCorreo(correo);
		return optUsuario.orElseThrow(() -> new Exception ("Usuario no encontrado con el DNI " + correo));
	
	}

	@Override
	public List<Usuario> listado() throws Exception {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();	}

	@Override
	public void eliminar(int id) throws Exception {
		Optional<Usuario> optUsuario = usuarioRepository.findById(id);
		Usuario usuario = optUsuario.orElseThrow(() -> new Exception ("Usuario no encontrado con el id " + id));
		usuarioRepository.delete(usuario);
				
	} 
	
	
	
	
	
}
