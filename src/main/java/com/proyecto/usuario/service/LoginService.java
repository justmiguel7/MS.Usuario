package com.proyecto.usuario.service;

import com.proyecto.usuario.dto.NewUserDto;
import com.proyecto.usuario.dto.ResponseLoginDto;
import com.proyecto.usuario.dto.UserDto;

public interface LoginService {
    ResponseLoginDto login(UserDto userDto) throws Exception;
    String create(NewUserDto userDto) throws Exception; // <-- Cambiado de void a String
}
