package com.Equipo2.RaceACar.controller;

import com.Equipo2.RaceACar.DTO.UsuarioDTO;
import com.Equipo2.RaceACar.DTO.UsuarioSinPassDTO;
import com.Equipo2.RaceACar.service.AutoService;
import com.Equipo2.RaceACar.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
        try {
            System.out.println(email);
            UsuarioSinPassDTO usuarioDTO = service.buscarPorEmail(email);
            return ResponseEntity.ok(usuarioDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario con el correo electrónico " + email + " no encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
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
