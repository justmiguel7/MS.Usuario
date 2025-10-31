package com.proyecto.usuario.service;

import java.time.LocalDateTime;
import java.util.Properties;
import java.util.UUID;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.usuario.entidades.Usuario;
import com.proyecto.usuario.entidades.VerificationToken;
import com.proyecto.usuario.repository.UsuarioRepository;
import com.proyecto.usuario.repository.VerificationTokenRepository;

@Service
public class VerificationServiceImp implements VerificationService {

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // ⚙️ Configuración de tu correo (puedes mover esto a application.properties si prefieres)
    private final String remitente = "tu_correo@gmail.com";
    private final String password = "tu_contraseña_de_aplicacion"; // ⚠️ usa contraseña de aplicación
    private final String host = "smtp.gmail.com";
    private final int port = 587;

    @Override
    public VerificationToken createVerificationToken(Usuario usuario) {
        VerificationToken token = new VerificationToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUsuario(usuario);
        token.setExpiryDate(LocalDateTime.now().plusHours(24));
        return tokenRepository.save(token);
    }

    @Override
    public void sendVerificationEmail(Usuario usuario, VerificationToken token) {
        String to = usuario.getCorreo();
        String subject = "Verifica tu cuenta - OdontologíaApp";
        String link = "http://localhost:8080/auth/verify?token=" + token.getToken();
        String content = """
                <html>
                    <body>
                        <h2>¡Bienvenido a OdontologíaApp!</h2>
                        <p>Por favor, verifica tu cuenta haciendo clic en el siguiente enlace:</p>
                        <p><a href="%s">Verificar cuenta</a></p>
                        <p>Este enlace expirará en 24 horas.</p>
                        <br>
                        <p>Atentamente,<br>Equipo de OdontologíaApp</p>
                    </body>
                </html>
                """.formatted(link);

        try {
            // Configuración SMTP
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", String.valueOf(port));

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(remitente, password);
                }
            });

            // Crear el mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(content, "text/html; charset=UTF-8");

            // Enviar el correo
            Transport.send(message);
            System.out.println("✅ Correo de verificación enviado a: " + to);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean verifyAccount(String tokenValue) throws Exception {
        VerificationToken token = tokenRepository.findByToken(tokenValue)
                .orElseThrow(() -> new Exception("Token inválido o inexistente."));

        if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new Exception("El token ha expirado. Solicita un nuevo correo de verificación.");
        }

        Usuario usuario = token.getUsuario();
        usuario.setEnabled(true);
        usuarioRepository.save(usuario);
        tokenRepository.delete(token);

        return true;
    }
}
