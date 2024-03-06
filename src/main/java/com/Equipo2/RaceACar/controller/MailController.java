package com.Equipo2.RaceACar.controller;

import com.Equipo2.RaceACar.Exceptions.MailSendingException;
import com.Equipo2.RaceACar.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MailController {
    private final MailService mailService;

    @PostMapping("/resend-mail")
    public ResponseEntity<String> resendMail(@RequestBody String email) {
        System.out.println(email);
        try {
            mailService.reSendMail(email);
            return ResponseEntity.ok("Correo reenviado exitosamente a " + email);
        } catch (MailSendingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al reenviar el correo electrónico: " + e.getMessage());
        }
    }
}
