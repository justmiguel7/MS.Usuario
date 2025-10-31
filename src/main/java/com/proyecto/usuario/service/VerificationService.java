package com.proyecto.usuario.service;

import com.proyecto.usuario.entidades.Usuario;
import com.proyecto.usuario.entidades.VerificationToken;

public interface VerificationService {
    VerificationToken createVerificationToken(Usuario usuario);
    void sendVerificationEmail(Usuario usuario, VerificationToken token);
    boolean verifyAccount(String tokenValue) throws Exception;
}
