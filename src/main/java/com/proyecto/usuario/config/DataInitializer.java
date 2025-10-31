package com.proyecto.usuario.config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.proyecto.usuario.entidades.RolUsuario;
import com.proyecto.usuario.entidades.Usuario;
import com.proyecto.usuario.repository.UsuarioRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                System.out.println("🔐 Creando usuarios iniciales...");

                Usuario paciente = new Usuario();
                paciente.setCorreo("paciente@clinicadental.com");
                paciente.setContrasena(encoder.encode("1234"));
                paciente.setDNI("10000001");
                paciente.setRol(RolUsuario.PACIENTE);
                paciente.setEnabled(true);
                paciente.setAccountNonExpired(true);
                paciente.setAccountNonLocked(true);
                paciente.setCredentialsNonExpired(true);

                Usuario odontologo = new Usuario();
                odontologo.setCorreo("odontologo@clinicadental.com");
                odontologo.setContrasena(encoder.encode("1234"));
                odontologo.setDNI("20000002");
                odontologo.setRol(RolUsuario.ODONTOLOGO);
                odontologo.setEnabled(true);
                odontologo.setAccountNonExpired(true);
                odontologo.setAccountNonLocked(true);
                odontologo.setCredentialsNonExpired(true);

                Usuario recepcionista = new Usuario();
                recepcionista.setCorreo("recepcionista@clinicadental.com");
                recepcionista.setContrasena(encoder.encode("1234"));
                recepcionista.setDNI("30000003");
                recepcionista.setRol(RolUsuario.RECEPCIONISTA);
                recepcionista.setEnabled(true);
                recepcionista.setAccountNonExpired(true);
                recepcionista.setAccountNonLocked(true);
                recepcionista.setCredentialsNonExpired(true);

                Usuario admin = new Usuario();
                admin.setCorreo("admin@clinicadental.com");
                admin.setContrasena(encoder.encode("admin123"));
                admin.setDNI("40000004");
                admin.setRol(RolUsuario.ADMINISTRADOR);
                admin.setEnabled(true);
                admin.setAccountNonExpired(true);
                admin.setAccountNonLocked(true);
                admin.setCredentialsNonExpired(true);

                usuarioRepository.save(paciente);
                usuarioRepository.save(odontologo);
                usuarioRepository.save(recepcionista);
                usuarioRepository.save(admin);

                System.out.println("Usuarios creados exitosamente");
            }
        };
    }
}
