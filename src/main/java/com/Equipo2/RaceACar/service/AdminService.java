package com.Equipo2.RaceACar.service;

import com.Equipo2.RaceACar.DTO.UsuarioSinPassDTO;
import com.Equipo2.RaceACar.User.Roles;
import com.Equipo2.RaceACar.User.Usuario;
import com.Equipo2.RaceACar.model.RolUsuario;
import com.Equipo2.RaceACar.repository.RolUsuarioRepository;
import com.Equipo2.RaceACar.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class AdminService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    UsuarioService service;
    @Autowired
    RolUsuarioService rolUsuarioService;

    @Transactional
    public void asignarRolUsuario(Long id) {
        cambiarRolUsuario(id, rolUsuarioService.buscarRolUsuario(1L));
    }
    @Transactional
    public void asignarRolAdmin(Long id) {
        cambiarRolUsuario(id, rolUsuarioService.buscarRolUsuario(2L));

    }

    private void cambiarRolUsuario(Long id, RolUsuario newRole) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));


        usuario.setRolUsuario(newRole);
        usuarioRepository.save(usuario);

        // Actualizar los detalles de usuario en la sesión si es necesario
        UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getUsername());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
