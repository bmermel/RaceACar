package com.Equipo2.RaceACar.controller;

import com.Equipo2.RaceACar.DTO.RolUsuarioDTO;
import com.Equipo2.RaceACar.service.RolUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rol")
@CrossOrigin(origins = "*")
public class RolUsuarioController {
    @Autowired
    private RolUsuarioService rolUsuarioService;

    @PostMapping()
    public ResponseEntity<?> crearRolUsuario(@RequestBody RolUsuarioDTO rolUsuarioDto){
        rolUsuarioService.guardarRolusuario(rolUsuarioDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
