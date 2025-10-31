package com.proyecto.usuario.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.usuario.dto.NewUserDto;
import com.proyecto.usuario.dto.ResponseLoginDto;
import com.proyecto.usuario.dto.UserDto;
import com.proyecto.usuario.service.LoginService;
import com.proyecto.usuario.service.VerificationService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@SecurityRequirement(name = "bearerAuth") 
public class AuthController {

    private final LoginService loginService;
    
    private final VerificationService verificationService;


    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDto> login(@RequestBody UserDto userDto) throws Exception {
        ResponseLoginDto response = loginService.login(userDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody NewUserDto newUserDto) throws Exception {
        String token = loginService.create(newUserDto); // recibimos token
        return ResponseEntity.status(HttpStatus.CREATED).body(token); // lo devolvemos al BFF
    }
    
    @GetMapping("/verify")
    public ResponseEntity<String> verifyAccount(@RequestParam("token") String token) {
        try {
            boolean verified = verificationService.verifyAccount(token);
            if (verified) {
                return ResponseEntity.ok("✅ Tu cuenta ha sido verificada exitosamente.");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token inválido.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Error: " + e.getMessage());
        }
    }

}