package com.proyecto.usuario.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.usuario.dto.NewUserDto;
import com.proyecto.usuario.dto.ResponseLoginDto;
import com.proyecto.usuario.dto.UserDto;
import com.proyecto.usuario.service.LoginService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@SecurityRequirement(name = "bearerAuth") 
public class AuthController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDto> login(@RequestBody UserDto userDto) throws Exception {
        ResponseLoginDto response = loginService.login(userDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody NewUserDto newUserDto) throws Exception {
        loginService.create(newUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}