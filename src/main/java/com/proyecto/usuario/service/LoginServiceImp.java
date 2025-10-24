package com.proyecto.usuario.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.usuario.entidades.Usuario;
import com.proyecto.usuario.dto.NewUserDto;
import com.proyecto.usuario.dto.ResponseLoginDto;
import com.proyecto.usuario.dto.UserDto;
import com.proyecto.usuario.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImp implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseLoginDto login(UserDto userDto) throws Exception {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword())
        );

        Optional<Usuario> userOpt = usuarioRepository.findByCorreo(userDto.getUsername());
        String token = jwtService.getToken(userOpt.get());

        return new ResponseLoginDto(token);
    }
    
    
    @Override
    public void create(NewUserDto userDto) throws Exception {
        System.out.println("🧠 [DEBUG] JSON recibido en /auth/register => " + userDto);
        
        Usuario usuario = new Usuario();
        usuario.setEnabled(true);
        usuario.setContrasena(passwordEncoder.encode(userDto.getPassword()));
        usuario.setCorreo(userDto.getUsername());
        usuario.setRol(userDto.getRol());
        usuario.setDNI(userDto.getDni()); // este es el campo que debería venir
        
        usuarioRepository.save(usuario);
    }
}
	