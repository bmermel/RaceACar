package com.Equipo2.RaceACar.service;

import com.Equipo2.RaceACar.DTO.UsuarioDTO;
import com.Equipo2.RaceACar.DTO.UsuarioSinPassDTO;
import com.Equipo2.RaceACar.User.Roles;
import com.Equipo2.RaceACar.User.Usuario;
import com.Equipo2.RaceACar.model.RolUsuario;
import com.Equipo2.RaceACar.repository.RolUsuarioRepository;
import com.Equipo2.RaceACar.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private RolUsuarioService rolUsuarioService;

    @Autowired
    private RolUsuarioRepository rolUsuarioRepository;
    @Autowired
    private UserDetailsService userDetailsService;

    public UsuarioSinPassDTO buscarPorEmail(String email) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            System.out.println(usuario.getRolUsuario().getRol().getAuthorities());
            return mapper.convertValue(usuario, UsuarioSinPassDTO.class);
        } else {
            throw new NoSuchElementException("Usuario con el correo electrónico " + email + " no encontrado");
        }
    }
    public void guardarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = mapper.convertValue(usuarioDTO, Usuario.class);
        usuario.setRolUsuario(rolUsuarioService.buscarRolUsuario(1L));
        usuarioRepository.save(usuario);
        }

    public List<UsuarioSinPassDTO> getUsuarios(){
        List<Usuario> listaDeUsuarios =  usuarioRepository.findAll();

        List<UsuarioSinPassDTO> usuarioSinPassDTOList = new ArrayList<>();

        for (int i = 0; i < listaDeUsuarios.size() ; i++) {
            UsuarioSinPassDTO usuario = mapper.convertValue(listaDeUsuarios.get(i), UsuarioSinPassDTO.class);
            usuarioSinPassDTOList.add(usuario);
        }

        return usuarioSinPassDTOList;


    }
    public String buscarNombreApellidoPorEmail(String email) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            return usuario.getNombre() + " " + usuario.getApellido();
        } else {
            return null; // O puedes lanzar una excepción indicando que el usuario no fue encontrado
        }
    }

    }
