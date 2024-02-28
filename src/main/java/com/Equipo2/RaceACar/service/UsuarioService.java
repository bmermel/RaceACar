package com.Equipo2.RaceACar.service;

import com.Equipo2.RaceACar.DTO.UsuarioDTO;
import com.Equipo2.RaceACar.User.Usuario;
import com.Equipo2.RaceACar.model.RolUsuario;
import com.Equipo2.RaceACar.repository.RolUsuarioRepository;
import com.Equipo2.RaceACar.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void guardarUsuario(UsuarioDTO usuarioDTO) {
        System.out.println(usuarioDTO);
        Usuario usuario = mapper.convertValue(usuarioDTO, Usuario.class);
        usuario.setRolUsuario(rolUsuarioService.buscarRolUsuario(1L));
        usuarioRepository.save(usuario);



        }
    }
