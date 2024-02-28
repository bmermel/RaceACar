package com.Equipo2.RaceACar.Auth;

import com.Equipo2.RaceACar.DTO.UsuarioDTO;
import com.Equipo2.RaceACar.JWT.JwtService;
import com.Equipo2.RaceACar.User.Roles;
import com.Equipo2.RaceACar.User.Usuario;
import com.Equipo2.RaceACar.model.RolUsuario;
import com.Equipo2.RaceACar.repository.UsuarioRepository;
import com.Equipo2.RaceACar.service.RolUsuarioService;
import com.Equipo2.RaceACar.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private final PasswordEncoder encoder;
    @Autowired
    private final UsuarioService usuarioService;
    @Autowired
    private final RolUsuarioService rolUsuarioService;
    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final ObjectMapper mapper;
    @Autowired
    private final AuthenticationManager authenticationManager;


    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserDetails usuario = usuarioRepository.findByEmail(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(usuario);

        return AuthResponse.builder()
                .token(token)
                .build();


    }

    public AuthResponse register(RegisterRequest request) {
        String encryptedPassword = encoder.encode(request.password);



        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                .nombre(request.nombre)
                .apellido(request.apellido)
                .email(request.email)
                .password(encryptedPassword)
                .documento(request.documento)
                .telefono(request.telefono)
                .rolUsuario(rolUsuarioService.buscarRolUsuario(1L))
                .build();
        System.out.println(rolUsuarioService.buscarRolUsuario(1L));
        System.out.println(usuarioDTO.toString());
        usuarioService.guardarUsuario(usuarioDTO);
        UserDetails user = mapper.convertValue(usuarioDTO, Usuario.class);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
