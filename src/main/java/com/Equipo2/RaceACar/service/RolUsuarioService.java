package com.Equipo2.RaceACar.service;


import com.Equipo2.RaceACar.DTO.RolUsuarioDTO;
import com.Equipo2.RaceACar.model.RolUsuario;
import com.Equipo2.RaceACar.repository.RolUsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolUsuarioService {
    private RolUsuarioRepository rolUsuarioRepository;
    private ObjectMapper mapper;

    @Autowired
    public RolUsuarioService(RolUsuarioRepository rolUsuarioRepository, ObjectMapper mapper) {
        this.rolUsuarioRepository = rolUsuarioRepository;
        this.mapper = mapper;
    }
    public void guardarRolusuario(RolUsuarioDTO rolUsuarioDto){
        RolUsuario rolUsuario = mapper.convertValue(rolUsuarioDto, RolUsuario.class);
        rolUsuarioRepository.save(rolUsuario);
    }
    public RolUsuarioDTO buscarRolUsuario(Long idRolUsuario) {
        Optional<RolUsuario> rolUsuario =  rolUsuarioRepository.findById(idRolUsuario);
        RolUsuarioDTO rolUsuarioDto = null;
        if(rolUsuario.isPresent()){
            rolUsuarioDto = mapper.convertValue(rolUsuario, RolUsuarioDTO.class);
        }
        return rolUsuarioDto;
    }

}
