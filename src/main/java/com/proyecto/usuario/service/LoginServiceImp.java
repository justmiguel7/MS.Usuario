package com.proyecto.usuario.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.usuario.entidades.Usuario;
import com.proyecto.usuario.entidades.VerificationToken;
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
    
    @Autowired
    private VerificationService verificationService;


    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseLoginDto login(UserDto userDto) throws Exception {
        Optional<Usuario> userOpt = usuarioRepository.findByCorreo(userDto.getUsername());
        Usuario usuario = userOpt.orElseThrow(() -> new Exception("Usuario no encontrado"));

        if (!usuario.isEnabled()) {
            throw new Exception("❌ Usuario no habilitado. Por favor verifica tu correo.");
        }

        // Autenticación normal
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword())
        );

        String token = jwtService.getToken(usuario);
        return new ResponseLoginDto(token);
    }

    
    
    @Override
    public String create(NewUserDto userDto) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEnabled(false);
        usuario.setContrasena(passwordEncoder.encode(userDto.getPassword()));
        usuario.setCorreo(userDto.getUsername());
        usuario.setRol(userDto.getRol());
        usuario.setDNI(userDto.getDni());
        usuarioRepository.save(usuario);

        VerificationToken token = verificationService.createVerificationToken(usuario);

        return token.getToken();
    }

}
	