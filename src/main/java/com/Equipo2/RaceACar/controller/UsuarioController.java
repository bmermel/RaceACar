package com.Equipo2.RaceACar.controller;

import com.Equipo2.RaceACar.DTO.UsuarioDTO;
import com.Equipo2.RaceACar.DTO.UsuarioSinPassDTO;
import com.Equipo2.RaceACar.User.Roles;
import com.Equipo2.RaceACar.service.AutoService;
import com.Equipo2.RaceACar.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    UsuarioService service;
    @Autowired
    private ObjectMapper mapper;


    @GetMapping("/{email}")
    public ResponseEntity<?> buscarUsuarioPorEmail(@PathVariable String email) {
        UsuarioSinPassDTO usuarioDTO = service.buscarPorEmail(email);
        System.out.println(usuarioDTO);
        System.out.println(usuarioDTO.getRolUsuario());
        System.out.println(usuarioDTO.getRolUsuario().getRol());
        System.out.println(usuarioDTO.getRolUsuario().getRol().getAuthorities());


        if(usuarioDTO.getRolUsuario().getId()==1)
        {
            return ResponseEntity.ok(usuarioDTO);

        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos para acceder a este recurso");
        }

    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllUsuarios(){
        try{
            List<UsuarioSinPassDTO> usuarios =  service.getUsuarios();
            if(!usuarios.isEmpty()){
                return ResponseEntity.ok(usuarios);
            }
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ocurrio un error al querer obtener la lista de usarios disponibles");
        }
    }
}
