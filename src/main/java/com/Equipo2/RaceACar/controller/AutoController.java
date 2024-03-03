package com.Equipo2.RaceACar.controller;

import com.Equipo2.RaceACar.DTO.AutoDTO;
import com.Equipo2.RaceACar.DTO.CrearAutoDTO;
import com.Equipo2.RaceACar.Exceptions.MailSendingException;
import com.Equipo2.RaceACar.model.Auto;
import com.Equipo2.RaceACar.service.AutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
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
    public ResponseEntity<?> crearAuto(@RequestBody CrearAutoDTO autoDTO) {
        try {
            service.crearAuto(autoDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UnsupportedEncodingException e) {
            return new ResponseEntity<>("Error al crear el auto: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarAuto(@PathVariable Long id, @RequestBody AutoDTO autoDTO){
        try {
            service.editarAuto(id, autoDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("El auto no fue encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al editar el auto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarAuto(@PathVariable Long id) {
        try {
            service.eliminarAuto(id);
            return new ResponseEntity<>("Auto Borrado Correctamente",HttpStatus.OK);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>("Auto no encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el auto", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Auto>> obtenerAutos() {
        try {
            List<Auto> autos = service.obtenerAutos();

            if (autos.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(autos);
            }
        } catch (Exception ex) {
            System.err.println("Error al obtener la lista de autos: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/disponibles")
    public ResponseEntity<List<Auto>> obtenerAutosDisponibles() {
        try {
            List<Auto> autosDisponibles = service.obtenerAutosDisponibles();
            if (autosDisponibles.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(autosDisponibles);
        } catch (MailSendingException.ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
