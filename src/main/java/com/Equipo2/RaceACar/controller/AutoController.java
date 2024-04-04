package com.Equipo2.RaceACar.controller;

import com.Equipo2.RaceACar.DTO.AutoDTO;
import com.Equipo2.RaceACar.DTO.CrearAutoDTO;
import com.Equipo2.RaceACar.DTO.EditarAutoDTO;
import com.Equipo2.RaceACar.Exceptions.MailSendingException;
import com.Equipo2.RaceACar.model.Auto;
import com.Equipo2.RaceACar.model.RolUsuario;
import com.Equipo2.RaceACar.service.AutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/autos")
@CrossOrigin(origins = "*")
public class AutoController {
    @Autowired
    AutoService service;
    @Autowired
    private ObjectMapper mapper;

    @PostMapping()
    public ResponseEntity<?> crearAuto(@RequestBody CrearAutoDTO autoDTO, @RequestHeader("idRol") RolUsuario idRol) {
        if(idRol.getId()==2||idRol.getId()==3) {

            try {
            service.crearAuto(autoDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UnsupportedEncodingException e) {
            return new ResponseEntity<>("Error al crear el auto: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }}
        else return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos para realizar esta acción");

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarAuto(@PathVariable Long id, @RequestBody EditarAutoDTO autoDTO,@RequestHeader("idRol") RolUsuario idRol){
        if(idRol.getId()==2||idRol.getId()==3) {

            try {
            service.editarAuto(id, autoDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("El auto no fue encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al editar el auto", HttpStatus.INTERNAL_SERVER_ERROR);
        }}
        else return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos para realizar esta acción");

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAuto(@PathVariable Long id,@RequestHeader("idRol") RolUsuario idRol) {
        if(idRol.getId()==2||idRol.getId()==3) {

            try {
            service.eliminarAuto(id);
            return new ResponseEntity<>("Auto Borrado Correctamente",HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("Auto no encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el auto", HttpStatus.NOT_FOUND);
        }}
        else return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos para realizar esta acción");
    }

    @GetMapping("/all")
    public ResponseEntity<?> obtenerAutos(@RequestHeader("idRol") RolUsuario idRol) {
        if (idRol.getId() == 2 || idRol.getId() == 3) {
            try {
                List<AutoDTO> autosDTO = service.obtenerAutos();

                if (autosDTO.isEmpty()) {
                    return ResponseEntity.noContent().build();
                } else {
                    return ResponseEntity.ok(autosDTO);
                }
            } catch (Exception ex) {
                System.err.println("Error al obtener la lista de autos: " + ex.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos para realizar esta acción");
        }
    }
    @GetMapping("/disponibles")
    public ResponseEntity<List<AutoDTO>> obtenerAutosDisponibles() {
        try {
            List<AutoDTO> autosDisponiblesDTO = service.obtenerAutosDisponiblesDTO();
            if (autosDisponiblesDTO.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(autosDisponiblesDTO);
        } catch (MailSendingException.ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{id}/cambiar-disponibilidad")
    public ResponseEntity<?> toggleDisponiblidad(@PathVariable Long id, @RequestHeader("idRol") RolUsuario idRol){
        if(idRol.getId()==2||idRol.getId()==3) {
            try {
                service.toggleDisponibilidad(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos para realizar esta acción");
    }

    @GetMapping("/disponiblesPorFecha")
    public ResponseEntity<List<AutoDTO>> obtenerAutosDisponiblesEntreFechas(
            @RequestParam("fechaInicio") String fechaInicio,
            @RequestParam("fechaFin") String fechaFin
    ) {
        LocalDate fechaInicioParsed = LocalDate.parse(fechaInicio);
        LocalDate fechaFinParsed = LocalDate.parse(fechaFin);
        List<AutoDTO> autosDisponibles = service.obtenerAutosDisponiblesEntreFechas(fechaInicioParsed, fechaFinParsed);

        if (autosDisponibles.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(autosDisponibles);
        }
    }
}
