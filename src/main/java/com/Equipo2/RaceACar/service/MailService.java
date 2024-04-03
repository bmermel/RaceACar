
package com.Equipo2.RaceACar.service;

import com.Equipo2.RaceACar.Exceptions.MailSendingException;
import com.Equipo2.RaceACar.model.Reserva;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UsuarioService usuarioService;
    @Value("${spring.mail.username}")
    private String fromMail;

    @Async
    public void sendMail(String email) throws MailSendingException {
        String username = usuarioService.buscarNombreApellidoPorEmail(email);
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(fromMail, "Race a Car");
            helper.setTo(email);
            helper.setText(MailTemplate.generateMail(username), true);
            helper.setSubject("Registro Exitoso! - Race A Car");
            mailSender.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new MailSendingException("Error al enviar el correo electrónico.", e);
        }
    }

    @Async
    public void reSendMail(String email) throws MailSendingException {
        String username = usuarioService.buscarNombreApellidoPorEmail(email);

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(fromMail, "Race a Car");
            helper.setTo(email);
            helper.setText(MailTemplate.generateMail(username), true);
            helper.setSubject("Reenvio de Registro Exitoso! - Race A Car");
            mailSender.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new MailSendingException("Error al enviar el correo electrónico.", e);
        }
    }

    @Async
    public void sendMailReservation(String email, Reserva reserva) throws MailSendingException {
        String username = usuarioService.buscarNombreApellidoPorEmail(email);
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(fromMail, "Race a Car");
            helper.setTo(email);
            helper.setText(MailTemplate.generateMailReservation(username,reserva), true);
            helper.setSubject("Reserva generada exitosamente! - Race A Car");
            mailSender.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new MailSendingException("Error al enviar el correo electrónico.", e);
        }
    }
    }

