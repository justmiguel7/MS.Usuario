package com.proyecto.usuario.dto;

import com.proyecto.usuario.entidades.RolUsuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(callSuper = false)
public class NewUserDto extends UserDto {
    private RolUsuario rol;
}
