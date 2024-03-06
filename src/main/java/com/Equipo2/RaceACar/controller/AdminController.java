package com.Equipo2.RaceACar.controller;

import com.Equipo2.RaceACar.model.RolUsuario;
import com.Equipo2.RaceACar.service.AdminService;
import com.Equipo2.RaceACar.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    AdminService service;
    @Autowired
    private ObjectMapper mapper;

    @PutMapping("/{id}/rol/user")
    public ResponseEntity<String> asignarRolUsuario(@PathVariable Long id, @RequestBody RolUsuario idRol) {
        if(idRol.getId()==3) {
            service.asignarRolUsuario(id);
            return ResponseEntity.ok("Rol de usuario asignado correctamente al usuario ID: " + id);
        } else return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos para realizar esta acción");
    }

    @PutMapping("/{id}/rol/admin")
    public ResponseEntity<String> asignarRolAdmin(@PathVariable Long id, @RequestBody RolUsuario idRol) {
        if(idRol.getId()==2 ||idRol.getId()==3) {

        service.asignarRolAdmin(id);
        return ResponseEntity.ok("Rol de admin asignado correctamente al usuario ID: " + id);
    }else return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos para realizar esta acción");
    }
}
