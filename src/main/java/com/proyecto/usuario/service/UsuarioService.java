package com.proyecto.usuario.service;

import java.util.List;

import com.proyecto.usuario.entidades.Usuario;
import com.proyecto.usuario.dto.UsuarioDTO;

public interface UsuarioService {

    Usuario BuscarUsuarioPorDNI(String DNI) throws Exception;
    Usuario BuscarUsuarioPorCorreo(String correo) throws Exception;
    List<Usuario> listado() throws Exception;
    void eliminar(int id) throws Exception;

    // 🔹 Método para registrar usuario
    Usuario registrarUsuario(UsuarioDTO usuarioDTO) throws Exception;
}
