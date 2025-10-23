package com.proyecto.usuario.dto;

import com.proyecto.usuario.entidades.RolUsuario;
import com.proyecto.usuario.entidades.Usuario;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDTO {

    private int id;

    @NotEmpty
    @Size(min = 8, max = 8, message = "la longitud del DNI debe ser de 8 digitos")
    private String DNI;

    @NotEmpty
    @Size(min = 1, max = 64, message = "la longitud del correo electronico debe ser entre 1 y 64 caracteres")
    private String correo;

    @NotEmpty
    @Size(min = 8, max = 16, message = "la longitud de la contraseña debe ser de entre 8 y 16 caracteres")
    private String contrasena;

    private RolUsuario rol;

    public Usuario toEntity() {
        Usuario usuario = new Usuario();
        usuario.setDNI(this.DNI);
        usuario.setCorreo(this.correo);
        usuario.setContrasena(this.contrasena);
        usuario.setRol(this.rol);
        return usuario;
    }
}
