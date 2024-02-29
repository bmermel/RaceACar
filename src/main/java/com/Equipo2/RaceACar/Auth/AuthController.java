package com.Equipo2.RaceACar.Auth;

import com.Equipo2.RaceACar.service.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private final AuthService authService;
    @Autowired
    private final MailService mailService;

    @PostMapping(value="login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
    @PostMapping(value="register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) throws MessagingException, UnsupportedEncodingException {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/send-mail")
    public ResponseEntity<String> sendMail(@RequestBody String email) {
        mailService.sendMail(email);

        return ResponseEntity.ok("Correo enviado con éxito.");
    }

}
