package com.proyecto.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.usuario.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByDNI(String DNI);
    Optional<Usuario> findByCorreo(String correo);
}
