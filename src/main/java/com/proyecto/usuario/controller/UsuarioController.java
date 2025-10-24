package com.proyecto.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.usuario.dto.UsuarioDTO;
import com.proyecto.usuario.entidades.Usuario;
import com.proyecto.usuario.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = usuarioService.registrarUsuario(usuarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Usuario registrado correctamente con ID: " + usuario.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar usuario: " + e.getMessage());
        }
    }

    @GetMapping("/listado")
    public ResponseEntity<List<Usuario>> listado() throws Exception {
        return ResponseEntity.ok(usuarioService.listado());
    }

    @GetMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable int id) throws Exception {
        usuarioService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
